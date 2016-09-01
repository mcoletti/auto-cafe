//package com.saat.auto.cafe.data.dao.mappers;
//
//import com.datastax.driver.core.Row;
//import com.datastax.driver.core.exceptions.DriverException;
//import com.datastax.driver.mapping.Result;
//import com.saat.auto.cafe.common.entitys.Vehicle;
//import com.saat.auto.cafe.common.entitys.Location;
//import com.saat.auto.cafe.common.entitys.VehicleDetail;
//
//import org.joda.time.DateTime;
//
///**
// * Created by micahcoletti on 8/2/16.
// */
//public class ClientVehicleRowMapper implements Result<Vehicle> {
////    @Override
////    public Vehicle mapRow(Row row, int rowNum) throws DriverException {
////        return Vehicle.builder()
////                .dealerId(row.getUUID("client_id"))
////                .vehicleId(row.getUUID("vehicle_id"))
////                .stockNum(row.getInt("stock_num"))
////                .shortDesc(row.getString("short_desc"))
////                .price(row.getDouble("price"))
////                .details(VehicleDetail.fromUdtValue(row.getUDTValue("details")))
////                .location(Location.fromUdtValue(row.getUDTValue("location")))
////                .createdBy(row.getString("created_by"))
////                .createdDtm(new DateTime(row.getDate("created_dtm")))
////                .modifiedBy(row.getString("modified_by"))
////                .modifiedDtm(new DateTime(row.getDate("modified_dtm"))).build();
////    }
//}
