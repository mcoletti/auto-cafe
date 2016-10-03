package com.saat.auto.cafe.common.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.saat.auto.cafe.common.entitys.Vehicle;

import java.util.UUID;

/**
 * Created by micahcoletti on 8/22/16.
 */
@Accessor
public interface VehicleAccessor {

    @Query("select * from vehicles where dealership_id = ?")
    Result<Vehicle> qryByDealerShipId(UUID clientId);

    @Query("select * from vehicles where dealership_id = ? and stock_num = ?")
    Result<Vehicle> qryByDealerShipIdAndVehicleId(UUID clientId, String stockNum);
}
