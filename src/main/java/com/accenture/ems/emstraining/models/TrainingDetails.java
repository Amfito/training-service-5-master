package com.accenture.ems.emstraining.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(description=  "Model of Training details data")
@Component
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingDetails {

    private static final String DATE_FORMAT_MESSAGE = "Date format: yyyy-mm-dd";
    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    @ApiModelProperty(
            notes = "Training Details id.",
            required = true, example = "1", hidden = false)
    private Long id;

    @ApiModelProperty(
            notes = "Training which relates to TrainingDetails ",
            required = true)
    @NotNull
    private Training training;

    @ApiModelProperty(
            notes = "Employee which relates to Training details",
            required = true)
    @NotNull
    private Long employee;

    @ApiModelProperty(
            notes = "Mentor which relates the Training details",
            required = false)
    private Long employeeRelation;

    @ApiModelProperty(
            notes= "Date of final presentation.",
            required = false, example = "2000-12-31")
    @Pattern(regexp = DATE_PATTERN, message = DATE_FORMAT_MESSAGE)
    private String finalPresentationDate;

    @ApiModelProperty(
            notes= "Date of training start.",
            required = true, example = "2000-12-31")
    @Pattern(regexp = DATE_PATTERN, message = DATE_FORMAT_MESSAGE)
    @NotEmpty
    private String startDate;

    @ApiModelProperty(
            notes= "Date of training end.",
            required = true, example = "2000-12-31")
    @Pattern(regexp = DATE_PATTERN, message = DATE_FORMAT_MESSAGE)
    @NotEmpty
    private String endDate;

}
