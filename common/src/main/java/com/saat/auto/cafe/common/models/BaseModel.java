package com.saat.auto.cafe.common.models;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by mcoletti on 5/10/16.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class BaseModel {
    @ApiModelProperty(position = 1, required = true, value = "The Primary Key Id")
    public int id;
}
