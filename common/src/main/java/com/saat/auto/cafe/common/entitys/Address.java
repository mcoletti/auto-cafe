package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.AutoCafeConstants.Schema;
import com.saat.auto.cafe.common.AutoCafeConstants.UdtTypes;
import com.saat.auto.cafe.common.models.AddressModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mcoletti on 6/17/16.
 */
@UDT(keyspace = "autocafe", name = "address")
@Data
@Builder
public class Address {
    @Field
    // @PrimaryKey
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
     * @param cluster
     * @return
     */
    public UDTValue toUdtValue(Cluster cluster) {

        // Default null values
        street2 = street2 != null ? street2 : "";
        phones = phones != null ? phones : new HashSet<>();
        UDTValue addressUdt = cluster.getMetadata().getKeyspace(Schema.KEY_SPACE).getUserType(UdtTypes.Address.NAME).newValue()
                .setString("street1", street1)
                .setString("street2", street2)
                .setString("city", city)
                .setString("state",state)
                .setInt("zip_code", zipCode)
                .setSet("phones", phones);
        return addressUdt;
    }

    /**
     * Static Helper Method that will convert from a UdtValue to an Address Object
     * @param udtValue
     * @return
     */
    public static Address fromUdtValue(UDTValue udtValue){


        return Address.builder()
                .street1(udtValue.getString("street1"))
                .street2(udtValue.getString("street2"))
                .city(udtValue.getString("city"))
                .state(udtValue.getString("state"))
                .zipCode(udtValue.getInt("zip_code"))
                .phones(udtValue.getSet("phones",String.class)).build();
    }


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
