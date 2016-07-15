package com.saat.auto.cafe.common.models;

import com.datastax.driver.core.UDTValue;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

import lombok.Data;

/**
 * Created by mcoletti on 6/17/16.
 */
@Table
@Data
public class ClientLocations {

    @PrimaryKey(value = "client_id")
    private UUID clientId;
    @Column
    private Location location;

    /**
     * Static Helper Method to convert from a User Defined Type to Location
     * @param locationUdtV
     * @return
     */
    public static Location fromUdtValue(UDTValue locationUdtV) {

        Location loc = new Location();
        loc.setName(locationUdtV.getString("name"));
        UDTValue addressUdtV = locationUdtV.getUDTValue("address");
        Address address = Address.fromUdtValue(addressUdtV);
        loc.setAddress(address);
        return loc;
    }

}
