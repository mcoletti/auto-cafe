package com.saat.auto.cafe.data.dao.mappers;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.schemabuilder.UDTType;
import com.saat.auto.cafe.common.db.SchemaConstants;
import com.saat.auto.cafe.common.db.SchemaConstants.ClientsLocationTable;
import com.saat.auto.cafe.common.models.ClientLocations;
import com.saat.auto.cafe.common.models.Location;

import org.springframework.cassandra.core.RowMapper;

/**
 * Created by micahcoletti on 7/12/16.
 */
public class ClientLocationRowMapper implements RowMapper<ClientLocations> {

    @Override
    public ClientLocations mapRow(Row row, int rowNum) throws DriverException {

        ClientLocations clRow = new ClientLocations();

        clRow.setClientId(row.getUUID(ClientsLocationTable.Columns.ClientId));
        clRow.setLocation(Location.fromUdtValue(row.getUDTValue(ClientsLocationTable.Columns.Location)));
        return clRow;
    }
}
