package com.saat.auto.cafe.data.dao.mappers;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.exceptions.DriverException;
import com.saat.auto.cafe.common.models.ClientLocations;
import com.saat.auto.cafe.common.models.Location;

import org.springframework.cassandra.core.RowMapper;

/**
 * Created by micahcoletti on 7/15/16.
 */
public class ClientLocationsRowMapper implements RowMapper<ClientLocations> {


    @Override
    public ClientLocations mapRow(Row row, int rowNum) throws DriverException {

        ClientLocations clientLoc = new ClientLocations();

        UDTValue locationUdtV = row.getUDTValue("location");
        clientLoc.setLocation(Location.fromUdtValue(locationUdtV));
        clientLoc.setClientId(row.getUUID("client_id"));
        return clientLoc;
    }
}
