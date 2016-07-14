package com.saat.auto.cafe.common.models;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import lombok.Data;

/**
 * Created by mcoletti on 6/17/16.
 */
@Table
@Data
public class ClientVehicles {

    @PrimaryKey(value = "client_id")
    private String clientId;
    @Column(value = "vehicle_id")
    private String vehicleId;
    @Column
    private String keyName;

}
