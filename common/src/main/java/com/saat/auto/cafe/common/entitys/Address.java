package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import com.saat.auto.cafe.common.AutoCafeConstants.Schema;
import com.saat.auto.cafe.common.AutoCafeConstants.UdtTypes;
import com.saat.auto.cafe.common.models.AddressModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mcoletti on 6/17/16.
 */
@UDT(name = "address", keyspace = "autocafe")
@Data
@NoArgsConstructor
public class Address {
    @Field
    private String street1;
    @Field
    private String street2;
    @Field
    private String city;
    @Field
    private String state;
    @Field
    private int zipCode;
    @Field
    private Set<String> phones;

    /**
     * Method to Convert to a UDT type Value
     */
    public UDTValue toUdtValue(Cluster cluster) {

        // Default null values
        street2 = street2 != null ? street2 : "";
        phones = phones != null ? phones : new HashSet<>();
        UDTValue addressUdt = cluster.getMetadata().getKeyspace(Schema.KEY_SPACE).getUserType(UdtTypes.Address.NAME).newValue()
                .setString("street1", street1)
                .setString("street2", street2)
                .setString("city", city)
                .setString("state", state)
                .setInt("zip_code", zipCode)
                .setSet("phones", phones);
        return addressUdt;
    }

    /**
     * Convert Entity object to Model object
     * @return
     */
    public AddressModel toModel() {
        return AddressModel.builder()
                .street1(street1)
                .street2(street2)
                .city(city)
                .state(state)
                .zipCode(zipCode)
                .phones(new ArrayList<>(phones)).build();
    }
}
