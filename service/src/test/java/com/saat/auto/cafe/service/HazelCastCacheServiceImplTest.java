package com.saat.auto.cafe.service;

import com.google.gson.Gson;

import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.interfaces.services.CacheService;
import com.saat.auto.cafe.common.interfaces.services.HazelCastService;
import com.saat.auto.cafe.common.models.VehicleDetailModel;
import com.saat.auto.cafe.service.cache.HazelCastCacheServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/26/16.
 */
public class HazelCastCacheServiceImplTest extends TestBase {

    @Autowired
    HazelCastService hazelCastService;

    private CacheService cacheService;
    private Gson gson;

    @BeforeClass
    public void init(){




        gson = new Gson();
        cacheService = new HazelCastCacheServiceImpl(AutoCafeConstants.Caches.VEHICLE_CACHE,gson);
    }


//    @Test
//    public void testInsertCacheEntry() throws Exception {
//        createVehicleRoot(UUID.randomUUID().toString());
//        cacheService.insertCacheEntry(ROOT_VDM.getId(),ROOT_VDM, VehicleDetailModel.class);
//
//        VehicleDetailModel vd = cacheService.getCacheEntry(ROOT_VDM.getId(),VehicleDetailModel.class);
//        assertThat(vd).isNotNull();
//        assertThat(vd.getId()).isEqualTo(ROOT_VDM.getId());
//    }
//
//    @Test(dependsOnMethods = {"testInsertCacheEntry"})
//    public void testRemoveCacheEntry(){
//        cacheService.removeEntry(ROOT_VDM.getId());
//        VehicleDetailModel vd = cacheService.getCacheEntry(ROOT_VDM.getId(),VehicleDetailModel.class);
//        assertThat(vd).isNull();
//
//    }
//
//    @Test(dependsOnMethods = {"testInsertCacheEntry"})
//    public void testGetCacheEntry() throws Exception {
//
//        VehicleDetailModel vd = cacheService.getCacheEntry(ROOT_VDM.getId(),VehicleDetailModel.class);
//        assertThat(vd).isNotNull();
//        assertThat(vd.getId()).isEqualTo(ROOT_VDM.getId());
//    }
//
//    @Test
//    public void testToJson() throws Exception {
//        setROOT_VDM(UUID.randomUUID().toString());
//
//        String preJsonValue = gson.toJson(ROOT_VDM,VehicleDetailModel.class);
//        String resultJson = cacheService.toJson(ROOT_VDM,VehicleDetailModel.class);
//        assertThat(preJsonValue).isEqualTo(resultJson);
//
//
//    }



}