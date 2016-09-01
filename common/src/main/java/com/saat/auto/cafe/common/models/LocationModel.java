package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.Location;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 8/8/16.
 */
@Data
@Builder
@ApiModel(description = "The Location Data Model")
public class LocationModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "The location Name"
    )
    private String name;
    @ApiModelProperty(
            position = 2,
            required = true,
            value = "The location address"
    )
    private AddressModel address;

    /**
     * Covert Model to Entity object
     * @return
     */
    public Location toEntity() {

        Location l = new Location();
        l.setName(name);
        l.setAddress(address.toEntity());
        return l;
    }
}
