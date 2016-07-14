package com.saat.auto.cafe.common.models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import lombok.Data;

/**
 * Created by mcoletti on 5/17/16.
 */
// @Table
@UDT(name = "location",keyspace = "autocafedb")
@Data
public class Location{

    @Field
    private String name;
    @Field
    private Address address;

    /**
     * Method to conventer to a UDT type Value
     * @param cluster
     * @return
     */
    public UDTValue toUdtValue(Cluster cluster){
        UDTValue locationUdt = cluster.getMetadata().getKeyspace("autocafedb").getUserType("location").newValue()
                .setString("name",name)
                .setUDTValue("address",address.toUdtValue(cluster));


        return locationUdt;
    }

    /**
     * Static Helper method to covert a UdtValue to a Location Object
     * @param udtValue the UdtValue
     * @return
     */
    public static Location fromUdtValue(UDTValue udtValue){

        Location location = new Location();
        location.setAddress(Address.fromUdtValue(udtValue));
        location.setName(udtValue.getString("name"));

        return location;
    }

}
