package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.util.Date;
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
    @Column(value = "created_uuid")
    private Date createdDtm;


    public Insert getInsertStatement(){


        Insert insert = QueryBuilder.insertInto("client_vehicles")
                .value("client_id",clientId).value("vehicle_id",vehicleId)
                .value("key_name",keyName).value("created_uuid", UUIDGen.getTimeUUID(createdDtm.getTime()));

        return insert;
    }

    /**
     * Method to covent the Entity object to the Model Object
     * @return
     */
    public ClientVehiclesModel toModel(){


        return ClientVehiclesModel.builder()
                .clientId(clientId.toString()).vehicleId(vehicleId.toString())
                .keyName(keyName).createdDtm(new DateTime(createdDtm.getTime()).toString()).build();
    }

}
