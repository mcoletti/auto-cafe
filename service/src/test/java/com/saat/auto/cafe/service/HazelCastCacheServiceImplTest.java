package com.saat.auto.cafe.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.HazelCastService;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;
import com.saat.auto.cafe.service.cache.HazelCastCacheServiceImpl;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/26/16.
 */
public class HazelCastCacheServiceImplTest extends TestBase {


    private CacheService cacheService;
    private Gson gson;
    private String vehicleId;

    @BeforeClass
    public void init(){


        HazelCastService hazelCastService = new HazelCastServiceImpl(hazelcastInstance);

        gson = new Gson();
        cacheService = new HazelCastCacheServiceImpl(hazelCastService,AutoCafeConstants.Caches.VEHICLE_CACHE,gson);
    }

    @Test
    public void testInsertCacheEntryGeneric() throws Exception {

        List<VehicleDetailsModel> vehicles = new ArrayList<>();

        setROOT_VDM(UUID.randomUUID().toString());
        vehicles.add(ROOT_VDM);

        Type type = new TypeToken<List<VehicleDetailsModel>>(){}.getType();
        cacheService.insertCacheEntry(ROOT_VDM.getId(),vehicles,type);

    }

    @Test
    public void testInsertCacheEntryNonGeneric() throws Exception {
        setROOT_VDM(UUID.randomUUID().toString());
        cacheService.insertCacheEntry(ROOT_VDM.getId(),ROOT_VDM);
    }

    @Test(dependsOnMethods = {"testInsertCacheEntryNonGeneric"})
    public void testIsInCacheAndRemove(){
        cacheService.insertCacheEntry(ROOT_VDM.getId(),ROOT_VDM.getClientId());
    }

    @Test(dependsOnMethods = {"testInsertCacheEntryNonGeneric"})
    public void testGetCacheEntry() throws Exception {



        VehicleDetailsModel vd = cacheService.getCacheEntry(vehicleId.toString(),VehicleDetailsModel.class);
        assertThat(vd).isNotNull();
        assertThat(vd.getId()).isEqualTo(vehicleId);
    }

    @Test
    public void testToJson() throws Exception {
        setROOT_VDM(UUID.randomUUID().toString());

        String preJsonValue = gson.toJson(ROOT_VDM,VehicleDetailsModel.class);
        String resultJson = cacheService.toJson(ROOT_VDM,VehicleDetailsModel.class);
        assertThat(preJsonValue).isEqualTo(resultJson);


    }



}