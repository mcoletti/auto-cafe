package com.saat.auto.cafe.data.dao.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleImage;

import java.util.UUID;

/**
 * Created by micahcoletti on 8/22/16.
 */
@Accessor
public interface VehicleAccessor {

    @Query("select * from vehicles where dealership_id = ?")
    Result<Vehicle> qryVehicleByDealerShipId(UUID dealerShipId);

    @Query("select * from vehicles where dealership_id = ? and id = ?")
    Result<Vehicle> qryVehicleByDealerShipIdAndVehicleId(UUID dealerShipId, UUID vehicleId);

    @Query("select * from vehicles where stock_num = ?")
    Result<Vehicle> qryByStockNum(String stockNum);

    @Query("select * from vehicle_images where dealership_id = ? and stock_num = ?")
    Result<VehicleImage> qryForImageList(UUID dealershipId, String stockNum);

    @Query("select * from vehicles where vin = ?")
    Result<Vehicle> qryByVin(String vin);
}
