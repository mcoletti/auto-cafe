package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.entitys.Location;
import com.saat.auto.cafe.common.interfaces.ClientsDao;
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
public class ClientDaoImplTest extends TestBase {


    private UUID clientId;
    @Autowired
    private ClientsDao clientsDao;

    @BeforeClass
    public void init(){

//        clientsDao = new ClientsDaoImpl(cassandraInstance);

    }



    @Test
    public void testUpsert() throws Exception {

        // Test Add New
        clientId = UUID.randomUUID();
        String user = "testUser";

        Map<String,Location> locationMap = new HashMap<>();
        locationMap.put("ogdenLocation",getLoc());
        locationMap.put("loganLocation",getLoc());

        Client c = new Client();
        c.setId(clientId);
        c.setClientName("testClient");
        c.setCreatedBy(user);
        c.setCreatedDtm(DateTime.now().toDate());
        c.setModifiedBy("tsetUser");
        c.setModifiedDtm(DateTime.now().toDate());
        c.setLocations(locationMap);

//                .id(clientId)
//                .createdBy(user)
//                .createdDtm(DateTime.now().toDate())
//                .clientName("testClient")
//                .modifiedBy("testUser")
//                .modifiedDtm(DateTime.now().toDate())
//                .locations(locationMap).build();


        c = clientsDao.upsert(c);
        assertThat(c).isNotNull();
        assertThat(c.getId()).isEqualTo(clientId);

        // Test Update
        user = "testUser2";
        DateTime updateDtm = DateTime.now();
        c.setModifiedBy(user);
        c.setModifiedDtm(updateDtm.toDate());

        Location location = new Location();
        location.setName("newLocation");
        location.setAddress(c.getLocations().get("ogdenLocation").getAddress());
//
//                .name("newLocation")
//                .address(c.getLocations().get("ogdenLocation").getAddress()).build();
        c.getLocations().put("newLocation",location);



        c = clientsDao.upsert(c);
        assertThat(c).isNotNull();
        assertThat(c.getModifiedBy()).isEqualTo(user);
    }

    @Test(dependsOnMethods = {"testUpsert"})
    public void testGetByUuid() throws Exception {

        Client cl = clientsDao.get(clientId);
        assertThat(cl).isNotNull();
        assertThat(cl.getId()).isEqualTo(clientId);

    }

    @Test
    public void testGetByName() throws Exception {
        Client cl = clientsDao.get(clientId);
        assertThat(cl).isNotNull();
        assertThat(cl.getId()).isEqualTo(clientId);
    }


    @Test
    public void testGetClientVehicles() throws Exception {

    }



}