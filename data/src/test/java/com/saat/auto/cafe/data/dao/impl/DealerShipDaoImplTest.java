package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.entitys.Location;
import com.saat.auto.cafe.common.interfaces.DealerShipDao;
import com.saat.auto.cafe.data.TestBase;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/12/16.
 */
public class DealerShipDaoImplTest extends TestBase {


    private UUID dealerId;
    @Autowired
    private DealerShipDao dealerShipDao;

    @BeforeClass
    public void init(){

//        dealerShipDao = new DealerShipDaoImpl(cassandraInstance);

    }



    @Test
    public void testUpsert() throws Exception {

        // Test Add New

        String user = "testUser";
        String dealerName = "testDealer";
        dealerId = UUID.randomUUID();

        Map<String,Location> locationMap = new HashMap<>();
        locationMap.put("ogdenLocation",getLoc());
        locationMap.put("loganLocation",getLoc());

        DealerShip dealerShip = new DealerShip();
        dealerShip.setId(dealerId);
        dealerShip.setName(dealerName);
        dealerShip.setCreatedBy(user);
        dealerShip.setCreatedDtm(DateTime.now().toDate());
        dealerShip.setModifiedBy("tsetUser");
        dealerShip.setModifiedDtm(DateTime.now().toDate());
        dealerShip.setLocations(locationMap);

//                .id(dealerId)
//                .createdBy(user)
//                .createdDtm(DateTime.now().toDate())
//                .clientName("testClient")
//                .modifiedBy("testUser")
//                .modifiedDtm(DateTime.now().toDate())
//                .locations(locationMap).build();


        dealerShip = dealerShipDao.upsert(dealerShip);
        assertThat(dealerShip).isNotNull();
        assertThat(dealerShip.getId()).isEqualTo(dealerId);

        // Test Update
        user = "testUser2";
        DateTime updateDtm = DateTime.now();
        dealerShip.setModifiedBy(user);
        dealerShip.setModifiedDtm(updateDtm.toDate());

        Location location = new Location();
        location.setName("newLocation");
        location.setAddress(dealerShip.getLocations().get("ogdenLocation").getAddress());
//
//                .name("newLocation")
//                .address(c.getLocations().get("ogdenLocation").getAddress()).build();
        dealerShip.getLocations().put("newLocation",location);



        dealerShip = dealerShipDao.upsert(dealerShip);
        assertThat(dealerShip).isNotNull();
        assertThat(dealerShip.getModifiedBy()).isEqualTo(user);
    }

    @Test(dependsOnMethods = {"testUpsert"})
    public void testGetDealerId() throws Exception {

        DealerShip cl = dealerShipDao.get(dealerId);
        assertThat(cl).isNotNull();
        assertThat(cl.getId()).isEqualTo(dealerId);

    }




}