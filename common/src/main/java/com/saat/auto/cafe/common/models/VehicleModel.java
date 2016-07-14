package com.saat.auto.cafe.common.models;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by mcoletti on 5/17/16.
 */
@EqualsAndHashCode(callSuper=false)
@Data
@ApiModel(description = "The Vehicle Model Data Entity", parent = BaseModel.class)
public class VehicleModel{
    @ApiModelProperty(position = 2, required = true, value = "Model Value i.e Honda")
    private String make;
    @ApiModelProperty(position = 3, required = true, value = "Make Value i.e Accord")
    private String model;
}
