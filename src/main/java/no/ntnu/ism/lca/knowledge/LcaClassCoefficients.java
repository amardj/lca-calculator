package no.ntnu.ism.lca.knowledge;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: Amar Jaiswal
 */
public interface LcaClassCoefficients {

    Map<String, List<Double>> ageCoeff            = new Age()           .classCoefficients;
    Map<String, List<Double>> genderCoeff         = new Gender()        .classCoefficients;
    Map<String, List<Double>> bmiCoeff            = new Bmi()           .classCoefficients;
    Map<String, List<Double>> eduCoeff            = new Education()     .classCoefficients;
    Map<String, List<Double>> painContinuousCoeff = new PainContinuous().classCoefficients;

    List<Double> painDurationCoeff       = Arrays.asList( -0.27, -0.05,  1.48,   0.08,   -1.23);
    List<Double> sleepCoeff              = Arrays.asList( -0.64, 0.39,   0.74,   -1.76,  1.27 );
    List<Double> activityCoeff           = Arrays.asList( -1.38, 1.67,   1.03,   -2.18,  0.86 );
    List<Double> painLastWeekCoeff       = Arrays.asList( -1.41, -0.86,  -1.33,  -1.64,  5.23 );
    List<Double> painSiteCountCoeff      = Arrays.asList( 0.18,  -0.05,  -0.20,  0.06,   0.01 );
    List<Double> orebQ7Coeff             = Arrays.asList( -0.13, -0.12,  -0.22,  -0.34,  0.82 );
    List<Double> pseqCoeff               = Arrays.asList( 0.13,  0.00,   0.27,   -1.36,  0.96 );
    List<Double> hsclMeanCoeff           = Arrays.asList( -0.73, -1.47,  -2.75,  6.82,   -1.87);
    List<Double> orebQ10Coeff            = Arrays.asList( 3.09,  4.23,   3.29,   -14.11, 3.50 );
    List<Double> workAbilityCoeff        = Arrays.asList( -0.27, -0.20,  -0.14,  -0.70,  1.31 );

    List<Double> painContLastWeekNoCoeff = Arrays.asList( 0.06,  0.05,   0.07,   0.06,   -0.23);
    List<Double> painContLastWeekYesCoeff= Arrays.asList( -0.06, -0.05,  -0.07,  -0.06,  0.236);
    List<Double> painDurOreboQ7Coeff     = Arrays.asList( 0.00,  -0.06,  -0.10,  0.04,   0.12 );
    List<Double> revActivityWorkAbilityCoeff    = Arrays.asList( 0.10,  -0.26,  -0.24,  0.37,   0.03 );

    List<Double> sleepHsclMeanCoeff      = Arrays.asList( -0.02, -0.07,  -0.29,  0.75,   -0.36);
    List<Double> painLastWeekSqCoeff     = Arrays.asList( 0.07,  0.07,   0.09,   0.08,   -0.31);
    List<Double> painsiteNumSqCoeff      = Arrays.asList( -0.06, 0.00,   0.07,   -0.08,  0.06 );
    List<Double> hsclMeanSqCoeff         = Arrays.asList( 0.12,  0.41,   1.36,   -3.36,  1.48 );
    List<Double> orebroQ7SqCoeff         = Arrays.asList( 0.00,  0.02,   0.03,   -0.01,  -0.04);
    List<Double> orebQ10MeanSqCoeff      = Arrays.asList( 0.10,  0.09,   -0.01,  -0.29,  0.13 );
    List<Double> revPseqSqCoeff          = Arrays.asList( 0.05,  0.32,   0.25,   -0.89,  0.27 );
    List<Double> revWorkAbilitySqCoeff   = Arrays.asList( -0.04, 0.13,   0.13,   -0.21,  0.00 );

     final class Age{
        private static final String age18to29 = "LT_30";
        private static final String age30to39 = "30-39";
        private static final String age40to49 = "40-49";
        private static final String age50to59 = "50-59";
        private static final String age60to69 = "MT_60";

        Map<String, List<Double>> classCoefficients = new TreeMap<>();;
        {
            List<Double> lcaAge18to29Coefficients = Arrays.asList( 0.15, -1.02,  0.16,  0.28,  0.42);
            List<Double> lcaAge30to39Coefficients = Arrays.asList( 0.00, -0.26,  0.20, -0.04,  0.10);
            List<Double> lcaAge40to49Coefficients = Arrays.asList(-0.17,  0.62,  0.25, -0.33, -0.36);
            List<Double> lcaAge50to59Coefficients = Arrays.asList(-0.11,  0.13, -0.08,  0.06, -0.01);
            List<Double> lcaAge60to69Coefficients = Arrays.asList( 0.13,  0.52, -0.53,  0.02, -0.14);

            classCoefficients.put(age18to29, lcaAge18to29Coefficients);
            classCoefficients.put(age30to39, lcaAge30to39Coefficients);
            classCoefficients.put(age40to49, lcaAge40to49Coefficients);
            classCoefficients.put(age50to59, lcaAge50to59Coefficients);
            classCoefficients.put(age60to69, lcaAge60to69Coefficients);
        }
    }

     final class Gender{
        private static final String genderFemale = "female";
        private static final String genderMale   = "male";

        final Map<String, List<Double>> classCoefficients = new TreeMap<>();
        {
            List<Double> lcaGenderFemaleCoefficients = Arrays.asList( -0.10, -0.07,  0.13, -0.03,  0.07);
            List<Double> lcaGenderMaleCoefficients   = Arrays.asList(  0.10,  0.07, -0.13,  0.03, -0.07);

            classCoefficients.put(genderFemale, lcaGenderFemaleCoefficients);
            classCoefficients.put(genderMale,   lcaGenderMaleCoefficients  );
        }
    }

     final class Bmi{
        private static final String bmiNormal       = "normal";
        private static final String bmiOverweight   = "overweight";
        private static final String bmiObese        = "obese";

        final Map<String, List<Double>> classCoefficients = new TreeMap<>();
        {
            List<Double> lcaBmiNormalCoefficients       = Arrays.asList(  0.06,  0.09,  -0.21,  0.33,   -0.28);
            List<Double> lcaBmiOverweightCoefficients   = Arrays.asList(  0.10,  0.01,   0.03,  0.22,   -0.36);
            List<Double> lcaBmiObeseCoefficients        = Arrays.asList( -0.16, -0.10,   0.18, -0.55,    0.63);

            classCoefficients.put(bmiNormal    ,  lcaBmiNormalCoefficients    );
            classCoefficients.put(bmiOverweight,  lcaBmiOverweightCoefficients);
            classCoefficients.put(bmiObese     ,  lcaBmiObeseCoefficients     );
        }
    }

     final class Education{
        private static final String eduHighSchool       = "highSchool";
        private static final String eduUpTo4YearsHE     = "upTo4YearsHE";
        private static final String eduMoreThan4YearsHE = "moreThan4YearsHE";

        final Map<String, List<Double>> classCoefficients = new TreeMap<>();
        {
            List<Double> lcaEduHighSchool         = Arrays.asList(  -0.17,   0.30,   0.19,  -0.56,  0.24);
            List<Double> lcaEduUpTo4YearsHE       = Arrays.asList(  -0.16,  -0.53,   0.18,   0.08,  0.42);
            List<Double> lcaEduMoreThan4YearsHE   = Arrays.asList(  -0.33,   0.23,  -0.38,   0.48, -0.66);

            classCoefficients.put(eduHighSchool      ,  lcaEduHighSchool      );
            classCoefficients.put(eduUpTo4YearsHE    ,  lcaEduUpTo4YearsHE    );
            classCoefficients.put(eduMoreThan4YearsHE,  lcaEduMoreThan4YearsHE);
        }
    }

     final class PainContinuous{
        private static final String painContinuousNo    = "no";
        private static final String painContinuousYes   = "yes";

        final Map<String, List<Double>> classCoefficients = new TreeMap<>();
        {
            List<Double> lcaPainContinuousNoCoefficients    = Arrays.asList( -0.19, -0.53,  -0.65,  0.01,   1.36);
            List<Double> lcaPainContinuousYesCoefficients   = Arrays.asList( 0.19,  0.53,   0.65,   -0.01,  -1.36);

            classCoefficients.put(painContinuousNo   ,  lcaPainContinuousNoCoefficients );
            classCoefficients.put(painContinuousYes  ,  lcaPainContinuousYesCoefficients);
        }
    }


    public static void main(String[] args) {
        System.out.println(LcaClassCoefficients.ageCoeff);
        System.out.println(LcaClassCoefficients.genderCoeff);
        System.out.println(LcaClassCoefficients.bmiCoeff);
        System.out.println(LcaClassCoefficients.eduCoeff);
        System.out.println(LcaClassCoefficients.painContinuousCoeff);
    }
}