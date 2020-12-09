package no.ntnu.ism.lca.knowledge;

import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Amar Jaiswal
 */
public interface LcaPriorIntercept {

     List<Double> a  = Arrays.asList(  0.31, 0.20,   0.22,	0.17,	0.10);

     List<Double> b  = Arrays.asList(
            Math.log(a.get(0)/a.get(0)),
            Math.log(a.get(1)/a.get(0)),
            Math.log(a.get(2)/a.get(0)),
            Math.log(a.get(3)/a.get(0)),
            Math.log(a.get(4)/a.get(0)));

     Double avgB = avg(b);

     List<Double> c  = Arrays.asList(
            b.get(0)-avgB,
            b.get(1)-avgB,
            b.get(2)-avgB,
            b.get(3)-avgB,
            b.get(4)-avgB);


    static Double avg(List<Double> list) {
        Double sum = 0.0;
        for (Double val: list)
            sum += val;
        return sum/list.size();
    }

     static void main(String[] args) {
        System.out.println("L3:M3 = " + a);
        System.out.println("L4:M4 = " + b);
        System.out.println("Q4    = " + avgB);
        System.out.println("L6:M6 = " + c);
    }
}
