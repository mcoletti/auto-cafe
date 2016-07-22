package com.saat.auto.cafe.data.dao.mappers;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.saat.auto.cafe.common.entitys.Location;
import com.saat.auto.cafe.common.entitys.VehicleDetails;

import org.joda.time.DateTime;
import org.springframework.cassandra.core.RowMapper;

/**
 * Created by mcoletti on 6/1/16.
 */
public class VehicleDetailsRowMapper implements RowMapper<VehicleDetails> {

    @Override
    public VehicleDetails mapRow(Row row, int rowNum) throws DriverException {


        VehicleDetails vd = VehicleDetails.builder()
                .id(row.getUUID("id")).clientId(row.getUUID("client_id"))
                .bodyStyle(row.getString("body_style")).extColor(row.getString("ext_color"))
                .intColor(row.getString("int_color")).keyName(row.getString("key_name"))
                .location(Location.fromUdtValue(row.getUDTValue("location")))
                .manufacture(row.getString("manufacture")).mileage(row.getString("mileage"))
                .make(row.getString("make")).model(row.getString("model"))
                .price(row.getDecimal("price")).stockNum(row.getInt("stock_num"))
                .trim(row.getString("trim"))
                .createdBy(row.getString("created_by")).createdDtm(new DateTime(row.getDate("created_dtm")))
                .modifiedBy(row.getString("modified_by")).modifiedDtm(new DateTime(row.getDate("modified_dtm")))
                .build();
        return vd;
    }
}
