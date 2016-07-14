package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.ClientsDao;
import com.saat.auto.cafe.common.models.Clients;
import com.saat.auto.cafe.data.DataBeans;

import org.joda.time.DateTime;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;

import static org.testng.Assert.*;

/**
 * Created by micahcoletti on 7/12/16.
 */
@ContextConfiguration(classes = DataBeans.class)
public class ClientsDaoImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private CassandraInstance cassandraInstance;
    private UUID clientId;
    private ClientsDao clientsDao;

    @BeforeClass
    public void init(){

        clientId = UUID.randomUUID();
        clientsDao = new ClientsDaoImpl(cassandraInstance);

    }

    static {
        System.setProperty("prop.path", ClientsDaoImplTest.class.getClassLoader().getResource("app.properties").getPath());
    }

    @Test
    public void testUpsert() throws Exception {

        Clients clients = new Clients();
        clients.setId(clientId);
        clients.setCreatedBy("testUser");
        clients.setClientName("testClient");
        clients.setCreatedDtm(DateTime.now().toDate());
        clients.setModifiedBy("testUser");
        clients.setModifiedDtm(DateTime.now().toDate());

        clients = clientsDao.upsert(clients);
        assertThat(clients).isNotNull();
        assertThat(clients.getId()).isEqualTo(clientId);
    }

    @Test(dependsOnMethods = {"testUpsert"})
    public void testGetByUuid() throws Exception {

        clientId = UUID.fromString("1c7bfd51-819f-4998-b164-c46fde2b8266");
        Clients cl = clientsDao.get(clientId);

        assertThat(cl).isNotNull();
        assertThat(cl.getId()).isEqualTo(clientId);

    }

    @Test
    public void testGetByName() throws Exception {

    }

    @Test
    public void testGetClientLocations() throws Exception {

    }

    @Test
    public void testGetClientVehicles() throws Exception {

    }

}