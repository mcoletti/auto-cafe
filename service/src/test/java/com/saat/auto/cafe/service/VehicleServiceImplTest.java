package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.VehicleModelCollection;
import com.saat.auto.cafe.common.models.VehicleModel;
import com.saat.auto.cafe.common.models.VehicleDetailModel;

import org.joda.time.DateTime;
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
    public void testUpsertDealerVehicle() throws Exception {

        setROOT_DVM();
        VehicleModel vehicle = vehicleService.upsertDealerShipVehicle(ROOT_DVM);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealerId()).isEqualTo(ROOT_DVM.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(ROOT_DVM.getStockNum());



        vehicle.setModifiedBy("micah");
        vehicle.setModifiedDtm(DateTime.now().toString());

        VehicleDetailModel detailModel = vehicle.getDetails();

        detailModel.setMake("honda");
        detailModel.setModel("accord");
        detailModel.setYear(2016);

        vehicle.setDetails(detailModel);



        vehicle = vehicleService.upsertDealerShipVehicle(vehicle);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getModifiedBy()).isEqualTo("micah");
        assertThat(vehicle.getDealerId()).isEqualTo(ROOT_DVM.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(ROOT_DVM.getStockNum());




    }

    @Test(dependsOnMethods = {"testUpsertDealerVehicle"})
    public void testGetByDealerId() throws Exception {

        VehicleModelCollection dvmc = vehicleService.get(ROOT_DVM.getDealerId());
        assertThat(dvmc).isNotNull();
        assertThat(dvmc.getClientVehicles()).isNotNull();
        assertThat(dvmc.getClientVehicles().size()).isGreaterThan(0);
    }

    @Test(dependsOnMethods = {"testUpsertDealerVehicle"})
    public void testGetByDealerIdAndStockNum() throws Exception {
        VehicleModel vehicle = vehicleService.get(ROOT_DVM.getDealerId(),ROOT_DVM.getStockNum());
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealerId()).isEqualTo(ROOT_DVM.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(ROOT_DVM.getStockNum());
    }

    //    @Test
//    public void testUpsertVehicleDetails() throws Exception {
//
//        setROOT_VDM(UUID.randomUUID().toString());
//        VehicleDetailModel vehicle = vehicleService.upsertVehicle(ROOT_VDM);
//        assertThat(vehicle).isNotNull();
//        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
//        assertThat(vehicle.getDealerId()).isEqualTo(ROOT_VDM.getDealerId());
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
//    public void testGetVehicleDetailsModelWithCache() throws Exception {
//
//
//        VehicleDetailModel vehicle = vehicleService.getVehicleDetailsModel(ROOT_VDM.getId(), ROOT_VDM.getDealerId());
//        assertThat(vehicle).isNotNull();
//        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
//        assertThat(vehicle.getDealerId()).isEqualTo(ROOT_VDM.getDealerId());
//
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
//    public void testGetVehicleDetailsModelCacheReset() throws Exception {
//
//
//        VehicleDetailModel vehicle = vehicleService.getVehicleDetailsModel(ROOT_VDM.getId(), ROOT_VDM.getDealerId(), true);
//        assertThat(vehicle).isNotNull();
//        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
//        assertThat(vehicle.getDealerId()).isEqualTo(ROOT_VDM.getDealerId());
//
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
//    public void testGetClientVehicles() throws Exception {
//
//        VehicleModelCollection collection = vehicleService.getVehicles(ROOT_VDM.getDealerId());
//        assertThat(collection).isNotNull();
//        assertThat(collection.getVehicles()).isNotNull();
//
//
//    }

}