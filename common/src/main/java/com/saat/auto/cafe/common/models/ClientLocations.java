package com.saat.auto.cafe.common.models;

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


}
