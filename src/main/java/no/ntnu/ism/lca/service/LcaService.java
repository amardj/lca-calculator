package no.ntnu.ism.lca.service;

import no.ntnu.ism.lca.knowledge.LcaClassCoefficients;
import no.ntnu.ism.lca.knowledge.LcaIntercept;
import no.ntnu.ism.lca.knowledge.LcaPriorIntercept;
import no.ntnu.ism.lca.model.Patient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static no.ntnu.ism.lca.knowledge.LcaClassCoefficients.*;

/**
 * @author: Amar Jaiswal
 */
@Service
public class LcaService {
    private static final Log log = LogFactory.getLog(LcaService.class);


    public int getLcaClass(Patient patient, boolean isStandardIntercept) {
        int lcaClass = -1;

        if (isPreconditionsMet(patient)){
            //log.info("The preconditions for the patient id : " + patient.getPatientId() + " : are met!");

            log.info("LCA intercept : " + LcaIntercept.intercept);

            List<Double> sumProductForAllClasses = getSumProductForAllClasses(patient);

            List<Double> logitScorePerClass = null;
            if(isStandardIntercept)
                logitScorePerClass = computeLogitBasedOnStandardIntercept(sumProductForAllClasses);
            else
                logitScorePerClass = computeLogitBasedOnPriorIntercept(sumProductForAllClasses);

            log.info("The logit scores : " + logitScorePerClass);

            List<Double> anonymScores  = null;

            if (null != logitScorePerClass)
                anonymScores  = Arrays.asList(
                        Math.exp(logitScorePerClass.get(0) - logitScorePerClass.get(0)),
                        Math.exp(logitScorePerClass.get(1) - logitScorePerClass.get(0)),
                        Math.exp(logitScorePerClass.get(2) - logitScorePerClass.get(0)),
                        Math.exp(logitScorePerClass.get(3) - logitScorePerClass.get(0)),
                        Math.exp(logitScorePerClass.get(4) - logitScorePerClass.get(0)));

            log.info("The anonymScores : " + anonymScores);

            Double anonymSum = Double.MAX_VALUE;

            if (null != anonymScores)
                anonymSum = sum(anonymScores);

            log.info("The anonymSum : " + anonymSum);

            List<Double> lcaClassScores  = Arrays.asList(
                    anonymScores.get(0)/anonymSum,
                    anonymScores.get(1)/anonymSum,
                    anonymScores.get(2)/anonymSum,
                    anonymScores.get(3)/anonymSum,
                    anonymScores.get(4)/anonymSum);

            log.info("The LCA class scores : " + lcaClassScores);

            int maxIndex = lcaClassScores.indexOf(Collections.max(lcaClassScores));

            lcaClass = maxIndex + 1;
        }

        return lcaClass;
    }

    private List<Double> getSumProductForAllClasses(Patient patient) {
        List<Double> sumProductForAllClasses = null;
        List<List<Double>> listOfList = new ArrayList<>();

        listOfList.add(elementWiseMultiplication(1, ageCoeff            .get(patient.getAge())));
        listOfList.add(elementWiseMultiplication(1, genderCoeff         .get(patient.getGender())));
        listOfList.add(elementWiseMultiplication(1, bmiCoeff            .get(patient.getBmi())));
        listOfList.add(elementWiseMultiplication(1, eduCoeff            .get(patient.getEdu())));
        listOfList.add(elementWiseMultiplication(1, painContinuousCoeff .get(patient.getPainContinuous())));

        // single valued
        listOfList.add(elementWiseMultiplication(patient.getPainDuration(), painDurationCoeff));
        listOfList.add(elementWiseMultiplication(patient.getSleep(), sleepCoeff));
        listOfList.add(elementWiseMultiplication(patient.getActivity(), activityCoeff));
        listOfList.add(elementWiseMultiplication(patient.getPainLastWeek(), painLastWeekCoeff));
        listOfList.add(elementWiseMultiplication(patient.getPainSiteCount(), painSiteCountCoeff));
        listOfList.add(elementWiseMultiplication(patient.getOrebQ7(), orebQ7Coeff));
        listOfList.add(elementWiseMultiplication(patient.getPseq(), pseqCoeff));
        listOfList.add(elementWiseMultiplication(patient.getHsclMean(), hsclMeanCoeff));
        listOfList.add(elementWiseMultiplication(patient.getOrebQ10(), orebQ10Coeff));
        listOfList.add(elementWiseMultiplication(patient.getWorkAbility(), workAbilityCoeff));

        if(patient.getPainContinuous().equals("no"))
            listOfList.add(elementWiseMultiplication(1, painContLastWeekNoCoeff));
        else
            listOfList.add(elementWiseMultiplication(1, painContLastWeekYesCoeff));

        listOfList.add(elementWiseMultiplication(patient.getPainDuration()*patient.getOrebQ7(), painDurOreboQ7Coeff));
        listOfList.add(elementWiseMultiplication(patient.getSleep()*patient.getHsclMean(), sleepHsclMeanCoeff));

        listOfList.add(elementWiseMultiplication(patient.getPainLastWeek()*patient.getPainLastWeek(), painLastWeekSqCoeff));
        listOfList.add(elementWiseMultiplication(patient.getPainSiteCount()*patient.getPainSiteCount(), painsiteNumSqCoeff));
        listOfList.add(elementWiseMultiplication(patient.getHsclMean()*patient.getHsclMean(), hsclMeanSqCoeff));
        listOfList.add(elementWiseMultiplication(patient.getOrebQ7()*patient.getOrebQ7(), orebroQ7SqCoeff));
        listOfList.add(elementWiseMultiplication(patient.getOrebQ10()*patient.getOrebQ10(), orebQ10MeanSqCoeff));
        listOfList.add(elementWiseMultiplication(patient.getPseq()*patient.getPseq(), revPseqSqCoeff));
        listOfList.add(elementWiseMultiplication(patient.getActivity()*patient.getWorkAbility(), revActivityWorkAbilityCoeff));
        listOfList.add(elementWiseMultiplication(patient.getWorkAbility()*patient.getWorkAbility(), revWorkAbilitySqCoeff));

        sumProductForAllClasses = sumIndexWise(listOfList);

        return sumProductForAllClasses;
    }

    public static List<Double> elementWiseMultiplication(double number, List<Double> doubleList) {
        // Double[] list = doubleList.toArray(new Double[doubleList.size()]);
        Double[] list = (Double[]) doubleList.toArray();
        Double[] product = (Double[]) Arrays.stream(list).map(i -> i*number).toArray(Double[]::new);
        List<Double> finalList = Arrays.asList(product);
        return finalList;
    }


    private List<Double> computeLogitBasedOnPriorIntercept(List<Double> sumProductForAllClasses) {
        List<Double> logitScorePerClass  = Arrays.asList(
                LcaIntercept.intercept.get(0) + sumProductForAllClasses.get(0),
                LcaIntercept.intercept.get(1) + sumProductForAllClasses.get(1),
                LcaIntercept.intercept.get(2) + sumProductForAllClasses.get(2),
                LcaIntercept.intercept.get(3) + sumProductForAllClasses.get(3),
                LcaIntercept.intercept.get(4) + sumProductForAllClasses.get(4));
        return logitScorePerClass;
    }

    private List<Double> computeLogitBasedOnStandardIntercept(List<Double> sumProductForAllClasses) {
        List<Double> logitScorePerClass  = Arrays.asList(
                LcaIntercept.intercept.get(0) - LcaPriorIntercept.c.get(0) + sumProductForAllClasses.get(0),
                LcaIntercept.intercept.get(1) - LcaPriorIntercept.c.get(1) + sumProductForAllClasses.get(1),
                LcaIntercept.intercept.get(2) - LcaPriorIntercept.c.get(2) + sumProductForAllClasses.get(2),
                LcaIntercept.intercept.get(3) - LcaPriorIntercept.c.get(3) + sumProductForAllClasses.get(3),
                LcaIntercept.intercept.get(4) - LcaPriorIntercept.c.get(4) + sumProductForAllClasses.get(4));
        return logitScorePerClass;
    }

    private boolean isPreconditionsMet(Patient patient) {
        boolean isValidFlag = true;

        return isValidFlag;
    }

    static Double sum(List<Double> list) {
        Double sum = 0.0;
        for (Double val: list)
            sum += val;
        return sum;
    }

    static List<Double> sumIndexWise(List<List<Double>> list) {
        List<Double> indexSum = new ArrayList(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        for( List<Double> indexVal : list){
            indexSum.set(0,indexSum.get(0)+indexVal.get(0));
            indexSum.set(1,indexSum.get(1)+indexVal.get(1));
            indexSum.set(2,indexSum.get(2)+indexVal.get(2));
            indexSum.set(3,indexSum.get(3)+indexVal.get(3));
            indexSum.set(4,indexSum.get(4)+indexVal.get(4));
            log.info("var.coeff : " + indexVal);
        }
        log.info("The sum-product : " + indexSum);
        return indexSum;
    }

    public static void main(String[] args) {
        Patient patient = new Patient();
        LcaService lcaService = new LcaService();
        System.out.println("\n\nLCA class is : " + lcaService.getLcaClass(patient, true));
    }
}