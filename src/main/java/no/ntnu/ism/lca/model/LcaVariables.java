package no.ntnu.ism.lca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
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

    @ApiModelProperty(position = 0, required = true, value = "Age Category : {LT_30, 30-39, 40-49, 50-59, MT_60}", example = "LT_30")
    String age = "LT_30";

    @ApiModelProperty(position = 1, required = true, value = "Gender Category : {female, male}", example = "female")
    String gender = "female";

    @ApiModelProperty(position = 2, required = true, value = "BMI Category : {normal, overweight, obese}", example = "normal")
    String bmi = "normal";

    @ApiModelProperty(position = 3, required = true, value = "Education Category : {highSchool, upTo4YearsHE, moreThan4YearsHE}", example = "highSchool")
    String edu = "highSchool";

    @ApiModelProperty(position = 4, required = true, value = "PainContinuous Category : {no, yes}", example = "no")
    String painContinuous = "yes";

    @ApiModelProperty(position = 5, required = true, value = "PainDuration range : [0, 10]", example = "2")
    int     painDuration = 2;

    @ApiModelProperty(position = 6, required = true, value = "Sleep range : [1, 5]", example = "0")
    int     sleep = 0;

    @ApiModelProperty(position = 7, required = true, value = "Reverse Activity range : [1, 4]", example = "3")
    int     activity = 3;

    @ApiModelProperty(position = 8, required = true, value = "Pain Intensity Last Week range : [0, 10]", example = "4")
    int     painLastWeek = 4;

    @ApiModelProperty(position = 9, required = true, value = "No. of Pain Sites range : [0, 10]", example = "1")
    int     painSiteCount = 1;

    @ApiModelProperty(position = 10, required = true, value = "Oreb Q10 range : [0, 10]", example = "5")
    int     orebQ7 = 5;

    @ApiModelProperty(position = 11, required = true, value = "Reverse PSEQ range : [0, 12]", example = "0")
    int     pseq = 0;

    @ApiModelProperty(position = 12, required = true, value = "Hscl Mean range : [0.0, 4.0]", example = "1.6")
    double  hsclMean = 1.6;

    @ApiModelProperty(position = 13, required = true, value = "Oreb Q10 range : [0, 10]", example = "5")
    int     orebQ10 = 5;

    @ApiModelProperty(position = 14, required = true, value = "Reverse Work Ability range : [0, 10]", example = "6")
    int     workAbility = 6;

    public Patient(){}
}
