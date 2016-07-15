package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.ClientsDao;
import com.saat.auto.cafe.common.models.Address;
import com.saat.auto.cafe.common.models.ClientLocations;
import com.saat.auto.cafe.common.models.Clients;
import com.saat.auto.cafe.common.models.Location;
import com.saat.auto.cafe.data.DataBeans;

import org.joda.time.DateTime;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

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

        clientsDao = new ClientsDaoImpl(cassandraInstance);

    }

    static {
        System.setProperty("prop.path", ClientsDaoImplTest.class.getClassLoader().getResource("app.properties").getPath());
    }

    @Test
    public void testUpsert() throws Exception {
        clientId = UUID.randomUUID();
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

    @Test(dependsOnMethods = {"testUpsert"})
    public void testGetClientLocations() throws Exception {

        List<ClientLocations> clientLocs = clientsDao.getClientLocations(clientId);

    }

    @Test
    public void testGetClientVehicles() throws Exception {

    }

    @Test
    public void testUpsertClientLoc() throws Exception {

        clientId = UUID.randomUUID();
        ClientLocations cLoc = new ClientLocations();
        cLoc.setClientId(clientId);
        cLoc.setLocation(getLoc());

        cLoc = clientsDao.upsertClientLoc(cLoc);
        assertThat(cLoc).isNotNull();
        assertThat(cLoc.getClientId()).isEqualTo(clientId);

    }

    private Location getLoc(){

        Location location = new Location();
        location.setName("testLoc");

        Address address = new Address();
        address.setStreet1("1234");
        address.setStreet2("1234");
        Set<String> phones = new HashSet<>();
        phones.add("801.499.9683");
        address.setPhones(phones);
        address.setCity("provo");
        address.setZipCode(84604);

        location.setAddress(address);

        return location;

    }

}