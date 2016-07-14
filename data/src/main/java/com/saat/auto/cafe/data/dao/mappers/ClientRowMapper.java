package com.saat.auto.cafe.data.dao.mappers;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.saat.auto.cafe.common.models.Clients;

import org.springframework.cassandra.core.RowMapper;

/**
 * Created by mcoletti on 6/16/16.
 */
public class ClientRowMapper implements RowMapper<Clients> {

    @Override
    public Clients mapRow(Row row, int rowNum) throws DriverException {

        Clients c = new Clients();
        c.setId(row.getUUID("id"));
        c.setClientName(row.getString("client_name"));
        c.setCreatedBy(row.getString("created_by"));
        c.setCreatedDtm(row.getDate("created_dtm"));
        c.setModifiedBy(row.getString("modified_by"));
        c.setModifiedDtm(row.getDate("modified_dtm"));
        return c;
    }
}
