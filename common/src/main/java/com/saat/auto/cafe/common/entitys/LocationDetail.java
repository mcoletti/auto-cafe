package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.UDT;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by micahcoletti on 10/1/16.
 */
@UDT(name = "location_detail", keyspace = "autocafe")
@Data
@NoArgsConstructor
public class LocationDetail {

    @Field
    private String name;
    @Field
    @Frozen
    private Address address;
}
