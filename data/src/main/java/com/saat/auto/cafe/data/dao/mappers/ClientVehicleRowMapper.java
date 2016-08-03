package com.saat.auto.cafe.data.dao.mappers;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.saat.auto.cafe.common.entitys.ClientVehicles;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.cassandra.core.RowMapper;

/**
 * Created by micahcoletti on 8/2/16.
 */
public class ClientVehicleRowMapper implements RowMapper<ClientVehicles> {
    @Override
    public ClientVehicles mapRow(Row row, int rowNum) throws DriverException {
        long time = UUIDGen.getAdjustedTimestamp(row.getUUID("created_uuid"));
        DateTime dateTime = new DateTime(time);
        return ClientVehicles.builder()
                .clientId(row.getUUID("client_id"))
                .vehicleId(row.getUUID("vehicle_id"))
                .keyName(row.getString("key_name"))
                .createdDtm(dateTime.toDate()).build();
    }
}
