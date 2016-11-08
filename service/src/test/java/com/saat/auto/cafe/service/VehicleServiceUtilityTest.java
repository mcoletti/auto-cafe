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


        vehicleServiceUtility.updateVehicleMakeTotals("445c245c-a886-4a9b-b49e-ad6455a69c84");
        DealerShipModel dealerShip = dealerShipService.get("445c245c-a886-4a9b-b49e-ad6455a69c84");
        assertThat(dealerShip).isNotNull();




    }

}