package no.ntnu.ism.lca.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * @author: Amar Jaiswal
 */
@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
// @NoArgsConstructor
public class Patient {

    String age = "LT_30";
    String gender = "female";
    String bmi = "normal";
    String edu = "highSchool";
    String painContinuous = "yes";

    int painDuration = 2;
    int sleep = 0;
    int activity = 3;
    int painLastWeek = 4;
    int painSiteCount = 1;
    int orebQ7 = 5;
    int pseq = 0;
    double hsclMean = 1.6;
    int orebQ10 = 5;
    int workAbility = 6;

    public Patient(){}
}
