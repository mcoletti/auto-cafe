package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/27/16.
 */
public class VehicleServiceImplTest extends TestBase {



    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    VehicleService vehicleService;


    @BeforeClass
    public void init() {

//        CacheService cacheService = new HazelCastCacheServiceImpl(hazelCastService);
//        vehicleService = new VehicleServiceImpl(cacheService, vehicleDao);

    }

    @Test
    public void testUpsertClientVehicle() throws Exception {

        setROOT_CVM();
        ClientVehiclesModel result = vehicleService.upsertClientVehicle(ROOT_CVM);

    }

    @Test
    public void testGetByClientId() throws Exception {


    }

    //    @Test
//    public void testUpsertVehicleDetails() throws Exception {
//
//        setROOT_VDM(UUID.randomUUID().toString());
//        VehicleDetailModel vehicle = vehicleService.upsertVehicle(ROOT_VDM);
//        assertThat(vehicle).isNotNull();
//        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
//        assertThat(vehicle.getClientId()).isEqualTo(ROOT_VDM.getClientId());
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
//    public void testGetVehicleDetailsModelWithCache() throws Exception {
//
//
//        VehicleDetailModel vehicle = vehicleService.getVehicleDetailsModel(ROOT_VDM.getId(), ROOT_VDM.getClientId());
//        assertThat(vehicle).isNotNull();
//        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
//        assertThat(vehicle.getClientId()).isEqualTo(ROOT_VDM.getClientId());
//
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
//    public void testGetVehicleDetailsModelCacheReset() throws Exception {
//
//
//        VehicleDetailModel vehicle = vehicleService.getVehicleDetailsModel(ROOT_VDM.getId(), ROOT_VDM.getClientId(), true);
//        assertThat(vehicle).isNotNull();
//        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
//        assertThat(vehicle.getClientId()).isEqualTo(ROOT_VDM.getClientId());
//
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
//    public void testGetClientVehicles() throws Exception {
//
//        ClientVehicleCollectionModel collection = vehicleService.getClientVehicles(ROOT_VDM.getClientId());
//        assertThat(collection).isNotNull();
//        assertThat(collection.getClientVehicles()).isNotNull();
//
//
//    }

}