package com.saat.auto.cafe.common.interfaces;

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

    @Query("select * from dealer_vehicles where dealer_id = ?")
    Result<Vehicle> qryByDealerId(UUID clientId);

    @Query("select * from dealer_vehicles where dealer_id = ? and stock_num = ?")
    Result<Vehicle> qryByDealerIdAndVehicleId(UUID clientId, String stockNum);
}
