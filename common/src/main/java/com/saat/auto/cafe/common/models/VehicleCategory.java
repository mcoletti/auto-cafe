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
@ApiModel(description = "The Vehicle Category Data Entity", parent = BaseModel.class)
public class VehicleCategory{
    @ApiModelProperty(position = 2, required = true, value = "Category Description")
    private String description;
}
