package com.saat.auto.cafe.common.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 8/3/16.
 */
@Builder
@Data
@ApiModel(description = "The Client Vehicle Data Model")
public class ClientVehiclesModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "The Client Id"
    )
    private String clientId;
    @ApiModelProperty(
            position = 2,
            required = true,
            value = "The Vehicle Id"
    )
    private String vehicleId;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The KeyName"
    )
    private String keyName;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The Created DateTime"
    )
    private String createdDtm;
}
