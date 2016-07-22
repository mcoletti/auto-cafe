package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import java.util.Set;

import lombok.Data;

/**
 * Created by mcoletti on 6/17/16.
 */
@UDT(keyspace = "autocafe", name = "address")
@Data
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

        UDTValue addressUdt = cluster.getMetadata().getKeyspace("autocafe").getUserType("address").newValue()
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

        Address address = new Address();
        address.setCity(udtValue.getString("city"));
        address.setStreet1(udtValue.getString("street1"));
        address.setStreet2(udtValue.getString("street2"));
        address.setCity(udtValue.getString("city"));
        address.setState(udtValue.getString("state"));
        address.setZipCode(udtValue.getInt("zip_code"));
        address.setPhones(udtValue.getSet("phones",String.class));
        return address;
    }


}
