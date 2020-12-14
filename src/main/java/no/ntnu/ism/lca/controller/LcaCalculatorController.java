package no.ntnu.ism.lca.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import no.ntnu.ism.lca.service.LcaService;
import no.ntnu.ism.lca.model.LcaVariables;

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
            notes = "This API needs a Patient json and type of intercept to be used. For details of LcaVariables, please explore under the Models section of Swagger UI."
    )
    public Map getLcaClass(
            @RequestParam (value = "StandardIntercept", required = false, defaultValue = "true") boolean typeOfIntercept,
            @RequestBody LcaVariables lcaVariables) {

        log.info("Trying to compute the LCA class...");
        Map lcaCalculatorResponse = lcaService.computeLcaClass(lcaVariables, typeOfIntercept);
        log.info("The LCA class is computed.");
        return lcaCalculatorResponse;
    }
}
