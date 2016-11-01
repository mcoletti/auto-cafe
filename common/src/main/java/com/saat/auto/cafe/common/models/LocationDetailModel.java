package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.LocationDetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by micahcoletti on 10/2/16.
 */
@ApiModel(description = "The Location Model")
@Data
public class LocationDetailModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "The location Name"
    )
    private String name;
    @ApiModelProperty(
            position = 1,
            required = true,
            value = "Location Address"
    )
    private AddressModel address;

    /**
     * Convert to entity object
     * @return
     */
    public LocationDetail toEntity() {
        LocationDetail ld = new LocationDetail();
        ld.setAddress(address.toEntity());
        ld.setName(name);
        return ld;
    }
}
