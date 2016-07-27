package com.saat.auto.cafe.service;

import com.google.gson.Gson;

import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.HazelCastService;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;
import com.saat.auto.cafe.data.dao.DaoFactory;
import com.saat.auto.cafe.data.dao.impl.VehicleDaoImpl;
import com.saat.auto.cafe.service.cache.HazelCastCacheServiceImpl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/27/16.
 */
public class VehicleServiceImplTest extends TestBase {


    @Inject
    private HazelCastService hazelCastService;

    @Inject
    CassandraInstance cassandraInstance;

    private VehicleDao vehicleDao;
    private VehicleService vehicleService;
    private CacheService cacheService;
    private String vehicleId;

    @BeforeClass
    public void init(){

        cacheService = new
                HazelCastCacheServiceImpl(hazelCastService, AutoCafeConstants.Caches.VEHICLE_CACHE,new Gson());
        vehicleDao = DaoFactory.getVehicleDao(cassandraInstance);
        vehicleService = new VehicleServiceImpl(cacheService, vehicleDao);

    }



    @Test
    public void testUpsertVehicleDetails() throws Exception {

        setROOT_VDM(UUID.randomUUID().toString());
        VehicleDetailsModel vehicle = vehicleService.upsertVehicle(ROOT_VDM);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
        assertThat(vehicle.getClientId()).isEqualTo(ROOT_VDM.getClientId());
    }

    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
    public void testGetVehicleDetailsModelWithCache() throws Exception {


        VehicleDetailsModel vehicle = vehicleService.getVehicleDetailsModel(ROOT_VDM.getId(),ROOT_VDM.getClientId());
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
        assertThat(vehicle.getClientId()).isEqualTo(ROOT_VDM.getClientId());

    }
    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
    public void testGetVehicleDetailsModelCacheReset() throws Exception {


        VehicleDetailsModel vehicle = vehicleService.getVehicleDetailsModel(ROOT_VDM.getId(),ROOT_VDM.getClientId(),true);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getId()).isEqualTo(ROOT_VDM.getId());
        assertThat(vehicle.getClientId()).isEqualTo(ROOT_VDM.getClientId());

    }

}