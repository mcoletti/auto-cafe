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
////                .dealershipId(row.getUUID("client_id"))
////                .dealershipId(row.getUUID("vehicle_id"))
////                .stockNum(row.getInt("stock_num"))
////                .options(row.getString("short_desc"))
////                .price(row.getDouble("price"))
////                .details(VehicleDetail.fromUdtValue(row.getUDTValue("details")))
////                .locations(Location.fromUdtValue(row.getUDTValue("locations")))
////                .createdUser(row.getString("created_by"))
////                .created(new DateTime(row.getDate("created_dtm")))
////                .modifiedUser(row.getString("modified_by"))
////                .modified(new DateTime(row.getDate("modified_dtm"))).build();
////    }
//}
