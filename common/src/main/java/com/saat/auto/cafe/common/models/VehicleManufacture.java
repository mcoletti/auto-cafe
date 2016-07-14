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
@ApiModel(description = "The Vehicle Manufacture Data Entity", parent = BaseModel.class)
public class VehicleManufacture {
    @ApiModelProperty(position = 2, required = true, value = "The full name of the manufacture i.e Ford")
    private String fullName;
    @ApiModelProperty(position = 3, required = true, value = "The description of the manufacture")
    private String description;
}
