package no.ntnu.ism.lca.service;

import no.ntnu.supportprim.lca.model.Patient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * @author: Amar Jaiswal
 */
@Service
public class LcaService {
    private final Log log = LogFactory.getLog(getClass());


    public int getLcaClass(Patient patient) {
        int lcaClass = -1;

        if (isPreconditionsMet(patient)){
            log.info("The preconditions for the patient id : " + patient.getPatientId() + " : are met!");


        }

        return lcaClass;
    }

    private boolean isPreconditionsMet(Patient patient) {
        boolean isValidFlag = false;

        return isValidFlag;
    }
}