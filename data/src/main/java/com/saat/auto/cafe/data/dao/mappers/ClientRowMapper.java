//package com.saat.auto.cafe.data.dao.mappers;
//
//import com.datastax.driver.core.Row;
//import com.datastax.driver.core.exceptions.DriverException;
//import com.saat.auto.cafe.common.entitys.DealerShip;
//import com.saat.auto.cafe.common.entitys.Location;
//
//import org.joda.time.DateTime;
//import org.springframework.cassandra.core.RowMapper;
//
///**
// * Created by mcoletti on 6/16/16.
// */
//public class ClientRowMapper implements RowMapper<DealerShip> {
//
//    @Override
//    public DealerShip mapRow(Row row, int rowNum) throws DriverException {
//
//        return DealerShip.builder()
//                .id(row.getUUID("id"))
//                .clientName(row.getString("client_name"))
//                .createdBy(row.getString("created_by"))
//                .createdDtm(new DateTime(row.getDate("created_dtm")))
//                .modifiedBy(row.getString("modified_by"))
//                .modifiedDtm(new DateTime(row.getDate("modified_dtm"))).build();
////                .location(Location.fromUdtValue(row.getUDTValue("location"))).build();
//    }
//}
