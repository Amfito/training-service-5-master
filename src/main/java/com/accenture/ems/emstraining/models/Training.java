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

@ApiModel(description=  "Model of Training data")
@Component
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Training {

    private static final String DATE_FORMAT_MESSAGE = "Date format: yyyy-mm-dd";
    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    @ApiModelProperty(
            notes = "Training id.",
            required = true, example = "1", hidden = false)
    private Long id;

    @ApiModelProperty(
            notes = "Training name. ")
    @NotNull
    private String name;

    @ApiModelProperty(
            notes = "Training type which relates to Training ",
            required = true)
    @NotNull
    private TrainingType trainingType;

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
