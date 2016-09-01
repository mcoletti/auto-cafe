package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.Address;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * Convert Address Model to Entity Object
     * @return
     */
    public Address toEntity() {

       Address a = new Address();
        a.setStreet1(street1);
        a.setStreet2(street2);
        a.setCity(city);
        a.setState(state);

        Set<String> phoneSet = phones != null ? new HashSet<>(phones) : null;
        a.setPhones(phoneSet);
        return a;
    }
}
