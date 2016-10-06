package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.entitys.LocationDetail;
import com.saat.auto.cafe.common.interfaces.daos.ClientDao;
import com.saat.auto.cafe.data.TestBase;

import org.apache.cassandra.utils.UUIDGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * Created by micahcoletti on 10/6/16.
 */
public class ClientDaoImplTest extends TestBase {


    @Autowired
    private ClientDao clientDao;
    private Client client;

    @Test
    public void testUpsert() throws Exception {

        LocationDetail ld = new LocationDetail();
        ld.setName("Main Office");

        Address address = new Address();
        address.setStreet1("12345");
        address.setStreet1("");
        address.setCity("Ogden");
        address.setState("UT");
        address.setZipCode(84604);
        ld.setAddress(address);

        UUID timeUuid = UUIDGen.getTimeUUID();
        String name = "Automatic Car Credit";
        client = new Client();
        client.setId(UUID.randomUUID());
        client.setName(name);
        client.setLocationDetail(ld);
        client.setCreatedUser("mcoletti");
        client.setCreated(timeUuid);
        client.setModifiedUser("mcoletti");
        client.setModified(timeUuid);


        clientDao.upsert(client);
        client = clientDao.get(client.getId());
        assertThat(client).isNotNull();
        assertThat(client.getName()).isEqualTo(name);
        assertThat(client.getCreated()).isEqualTo(timeUuid);
        assertThat(client.getModified()).isEqualTo(timeUuid);


        // Update the Client Data
        ld = client.getLocationDetail();
        ld.setName("main Office 2");
        address = new Address();
        address.setStreet1("9876");
        address.setStreet1("12345");
        address.setCity("Logan");
        address.setState("UT");
        address.setZipCode(86654);
        ld.setAddress(address);
        client.setLocationDetail(ld);

        timeUuid = UUIDGen.getTimeUUID();
        client.setModified(timeUuid);
        client.setCreatedUser("acoletti");
        client.setLocationDetail(ld);

        clientDao.upsert(client);
        client = clientDao.get(client.getId());
        assertThat(client).isNotNull();
        assertThat(client.getName()).isEqualTo(name);
        assertThat(client.getModified()).isEqualTo(timeUuid);
        assertThat(client.getLocationDetail().getName()).isEqualTo(ld.getName());


    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testGetByName() throws Exception {

    }

}