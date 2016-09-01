//package com.saat.auto.cafe.data.dao.mappers;
//
//import com.datastax.driver.core.Row;
//import com.datastax.driver.core.exceptions.DriverException;
//import com.saat.auto.cafe.common.entitys.VehicleImage;
//
//import org.springframework.cassandra.core.RowMapper;
//
///**
// * Created by micahcoletti on 7/20/16.
// */
//public class VehicleImagesRowMapper implements RowMapper<VehicleImage> {
//
//
//    @Override
//    public VehicleImage mapRow(Row row, int rowNum) throws DriverException {
//
//        return VehicleImage.builder()
//                .vehicleId(row.getUUID("vehicle_id"))
//                .imgType(row.getString("img_type")).imgName(row.getString("img_name"))
//                .imgCdnLoc(row.getString("img_cdn_loc")).imgSize(row.getInt("img_size"))
//                .imgSuffix(row.getString("img_suffix")).imgOrder(row.getInt("img_order")).build();
//    }
//}
