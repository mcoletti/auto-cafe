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
@ApiModel(description = "DealerShip Vehicles Collection Model")
public class VehicleModelCollection {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "An array of DealerShip Vehicles"
    )
    private List<VehicleModel> clientVehicles;


    public static String getCollectionCacheKey(String clientId){

        return String.format("%s-collection",clientId);

    }

}
