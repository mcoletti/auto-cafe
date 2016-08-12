package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.Address;

import java.util.HashSet;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 8/8/16.
 */
@Data
@Builder
@ApiModel(description = "The Address Data Model")
public class AddressModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "Street1 Value"
    )
    private String street1;
    @ApiModelProperty(
            position = 2,
            required = true,
            value = "Street2 Value"
    )
    private String street2;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The City"
    )
    private String city;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The State"
    )
    private String state;
    @ApiModelProperty(
            position = 5,
            required = true,
            value = "The Zip Code"
    )
    private int zipCode;
    @ApiModelProperty(
            position = 6,
            required = true,
            value = "List of Phone Numbers"
    )
    private List<String> phones;

    public Address toEntity() {
        return null; //  Address.builder()
//                .street1(street1)
//                .street2(street2)
//                .city(city)
//                .state(state)
//                .zipCode(zipCode)
//                .phones(new HashSet<>(phones)).build();
    }
}
