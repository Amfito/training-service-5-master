package com.accenture.ems.emstraining.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@ApiModel(description=  "Model of Training type data")
@Component
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainingType {

    @ApiModelProperty(
            notes = "Training type id.",
            required = true, example = "1", hidden = false)
    private Long id;

    @ApiModelProperty(
            notes = "Training type. ")
    @NotNull
    private String type;
}
