package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.DealerShipModel;
import com.saat.auto.cafe.service.impl.VehicleServiceUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 11/7/16.
 */
public class VehicleServiceUtilityTest extends TestBase {


    @Autowired
    VehicleServiceUtility vehicleServiceUtility;

    @Autowired
    DealerShipService dealerShipService;


    @Test
    public void testUpdateVehicleMakeTotals() throws Exception {


        vehicleServiceUtility.updateVehicleMakeTotals("216b8863-8247-4e5c-a0ab-7bea34fae324");
        DealerShipModel dealerShip = dealerShipService.get("216b8863-8247-4e5c-a0ab-7bea34fae324");
        assertThat(dealerShip).isNotNull();




    }

}