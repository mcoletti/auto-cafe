package com.saat.auto.cafe.common.models;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 8/3/16.
 */
@Builder
@Data
@ApiModel(description = "Client Vehicles Collection Model")
public class ClientVehicleCollectionModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "An array of Client Vehicles"
    )
    private List<ClientVehiclesModel> clientVehicles;


    public static String getCollectionCacheKey(String clientId){

        return String.format("%s-collection",clientId);

    }
}
