package com.saat.auto.cafe.common.models;

import org.joda.time.DateTime;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by mcoletti on 5/10/16.
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class BaseModelExtended{
    @ApiModelProperty(position = 2, required = true, value = "Created by User")
    public String createdBy;
    @ApiModelProperty(position = 3, required = true, value = "Created by DataTime")
    public DateTime createdDtm;
    @ApiModelProperty(position = 4, required = true, value = "Modified by User")
    public String modifiedBy;
    @ApiModelProperty(position = 5, required = true, value = "Modified by DataTime")
    public DateTime modifiedDtm;
}
