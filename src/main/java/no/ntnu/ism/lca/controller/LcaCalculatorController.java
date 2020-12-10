package no.ntnu.ism.lca.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import no.ntnu.ism.lca.service.LcaService;
import no.ntnu.ism.lca.model.Patient;

import java.util.Map;

/**
 * @author: Amar Jaiswal
 */
@RestController
//@RequestMapping("lca")
//@CrossOrigin(allowCredentials="true")
public class LcaCalculatorController {

    private final Log log = LogFactory.getLog(getClass());

    @Autowired
    private LcaService lcaService;

    @GetMapping("/")
    public String health() {
        return "I am Ok!";
    }

    @PostMapping(value= "/getLcaClass")
    @ApiOperation(value = "${LcaCalculatorController.getLcaClass}",//
            notes = "This API needs a Patient json and type of intercept to be used. For details of Patient json, please explore under the Models section of Swagger UI."
    )
    public Map getLcaClass(
            @RequestParam (value = "StandardIntercept", required = false, defaultValue = "true") boolean typeOfIntercept,
            @RequestBody Patient patient) {

        log.info("Saving the patient data to DB");
        Map lcaCalculatorResponse = lcaService.computeLcaClass(patient, typeOfIntercept);
        return lcaCalculatorResponse;
    }
}
