package com.saat.auto.cafe.service;

import com.google.gson.Gson;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.HazelCastService;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;

import org.joda.time.DateTime;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.UUID;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/26/16.
 */
@ContextConfiguration(classes = ServiceBeans.class)
public class HazelCastCacheServiceImplTest extends AbstractTestNGSpringContextTests {



    @Inject
    HazelcastInstance hazelcastInstance;


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
    public void testInsertCacheEntry() throws Exception {

        vehicleId = UUID.randomUUID().toString();
        VehicleDetailsModel vdM = getVDM(vehicleId);

        String cacheValue = cacheService.toJson(vdM,VehicleDetails.class);
        cacheService.insertCacheEntry(vdM.getId(),cacheValue);

    }


    @Test(dependsOnMethods = {"testInsertCacheEntry"})
    public void testGetCacheEntry() throws Exception {



        VehicleDetails vd = cacheService.getCacheEntry(vehicleId.toString(),VehicleDetails.class);
        assertThat(vd).isNotNull();



    }

    @Test
    public void testToJson() throws Exception {

        VehicleDetailsModel vdm = getVDM(UUID.randomUUID().toString());

        String preJsonValue = gson.toJson(vdm,VehicleDetailsModel.class);
        String resultJson = cacheService.toJson(vdm,VehicleDetailsModel.class);
        assertThat(preJsonValue).isEqualTo(resultJson);


    }

    private VehicleDetailsModel getVDM(String vehicleId){
        VehicleDetailsModel vd = VehicleDetailsModel.builder()
                .id(vehicleId)
                .clientId("395b2f0c-8008-410c-8402-fb64a3a7a295")
                .keyName("micah").stockNum(1234)
                .extColor("black").intColor("red")
                .trim("na").price(new BigDecimal(15500.00))
                .mileage("115,000").category("sedans")
                .make("Honda").model("Accord")
                .year(2003)
                .bodyStyle("sedan")
                .manufacture("Honda")
                .location(null)
                .createdBy("testUser")
                .createdDtm(DateTime.now().toString())
                .modifiedBy("testUser")
                .modifiedDtm(DateTime.now().toString()).build();

        return vd;
    }

}