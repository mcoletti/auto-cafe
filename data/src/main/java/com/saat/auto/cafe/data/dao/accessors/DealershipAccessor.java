package com.saat.auto.cafe.data.dao.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.entitys.DealershipLot;
import com.saat.auto.cafe.common.entitys.Vehicle;

import java.util.UUID;

/**
 * Created by micahcoletti on 10/6/16.
 */
@Accessor
public interface DealershipAccessor {

    @Query("select * from dealerships where id = ?")
    Result<DealerShip> qryById(UUID id);

    @Query("select * from dealerships where client_id = ?")
    Result<DealerShip> qryByClientId(UUID clientId);

    @Query("select * from dealership_lots where dealership_id = ?")
    Result<DealershipLot> qryForDealerShipLots(UUID dealerShipId);

}
