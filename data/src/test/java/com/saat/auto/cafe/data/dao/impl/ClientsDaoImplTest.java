package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.interfaces.ClientsDao;
import com.saat.auto.cafe.common.models.Clients;
import com.saat.auto.cafe.data.TestBase;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/12/16.
 */
public class ClientsDaoImplTest extends TestBase {


    private UUID clientId;
    private ClientsDao clientsDao;

    @BeforeClass
    public void init(){

        clientsDao = new ClientsDaoImpl(cassandraInstance);

    }



    @Test
    public void testUpsert() throws Exception {

        // Test Add New
        clientId = UUID.randomUUID();
        String user = "testUser";
        Clients clients = new Clients();
        clients.setId(clientId);
        clients.setCreatedBy(user);
        clients.setClientName("testClient");
        clients.setCreatedDtm(DateTime.now());
        clients.setModifiedBy(user);
        clients.setModifiedDtm(DateTime.now());
        clients.setLocation(getLoc());

        clients = clientsDao.upsert(clients);
        assertThat(clients).isNotNull();
        assertThat(clients.getId()).isEqualTo(clientId);


        // Test Update
        user = "testUser2";
        DateTime updateDtm = DateTime.now();
        clients.setModifiedBy(user);
        clients.setModifiedDtm(updateDtm);

        clients = clientsDao.upsert(clients);
        assertThat(clients).isNotNull();
        assertThat(clients.getModifiedBy()).isEqualTo(user);
    }

    @Test(dependsOnMethods = {"testUpsert"})
    public void testGetByUuid() throws Exception {

        Clients cl = clientsDao.get(clientId);
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