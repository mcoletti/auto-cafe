package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

//import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by mcoletti on 6/17/16.
 */
@Table(
        name = "client_vehicles",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@Builder
public class ClientVehicles {

    @PrimaryKey(value = "client_id")
    @Getter
    private UUID clientId;
    @Column(value = "vehicle_id")
    private UUID vehicleId;
    @Column(value = "key_name")
    private String keyName;


    public Insert getInsertStatement(){


        Insert insert = QueryBuilder.insertInto("client_vehicles")
                .value("client_id",clientId).value("vehicle_id",vehicleId)
                .value("key_name",keyName);

        return insert;
    }

}
