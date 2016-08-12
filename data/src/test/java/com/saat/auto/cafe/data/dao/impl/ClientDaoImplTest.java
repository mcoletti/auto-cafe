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
        Client client = Client.builder()
                .id(clientId)
                .createdBy(user)
                .createdDtm(DateTime.now())
                .clientName("testClient")
                .modifiedBy("testUser")
                .modifiedDtm(DateTime.now())
                .location(getLoc()).build();


        client = clientsDao.upsert(client);
        assertThat(client).isNotNull();
        assertThat(client.getId()).isEqualTo(clientId);

        // Test Update
        user = "testUser2";
        DateTime updateDtm = DateTime.now();
        client.setModifiedBy(user);
        client.setModifiedDtm(updateDtm);

        Location location = Location.builder()
                .name("newLocation")
                .address(client.getLocation().getAddress()).build();
        client.setLocation(location);

        client = clientsDao.upsert(client);
        assertThat(client).isNotNull();
        assertThat(client.getModifiedBy()).isEqualTo(user);
    }

    @Test(dependsOnMethods = {"testUpsert"})
    public void testGetByUuid() throws Exception {

        Client cl = clientsDao.get(clientId);
        assertThat(cl).isNotNull();
        assertThat(cl.getId()).isEqualTo(clientId);

    }

    @Test
    public void testGetByName() throws Exception {

    }


    @Test
    public void testGetClientVehicles() throws Exception {

    }



}