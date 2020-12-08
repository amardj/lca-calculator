package no.ntnu.ism.lca.controller;

import io.swagger.annotations.ApiOperation;
import no.ntnu.ism.lca.service.LcaService;
import no.ntnu.ism.lca.model.Patient;
import no.ntnu.ism.lca.service.LcaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Amar Jaiswal
 */
@RestController
@RequestMapping("/lca")
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
            notes = "This API needs a Patient json."
    )
    public int getLcaClass(@RequestBody Patient patient) {

        log.info("Saving the patient data to DB");
        int lcaClass = lcaService.getLcaClass(patient);
        return lcaClass;
    }
}
