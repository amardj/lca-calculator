package no.ntnu.ism.lca.knowledge;

import java.util.Arrays;
import java.util.List;

import static no.ntnu.ism.lca.knowledge.LcaPriorIntercept.*;

/**
 * @author: Amar Jaiswal
 */
public interface LcaIntercept {
    List<Double> intercept  = Arrays.asList(  18.51,	-0.81,	3.19,	22.81,	-43.69);
    List<Double> interceptBasedOnPriorIntercept  = Arrays.asList(
            intercept.get(0) - c.get(0),
            intercept.get(1) - c.get(1),
            intercept.get(2) - c.get(2),
            intercept.get(3) - c.get(3),
            intercept.get(4) - c.get(4));
}
