package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.services.ClientService;
import com.saat.auto.cafe.common.models.AddressModel;
import com.saat.auto.cafe.common.models.ClientModel;
import com.saat.auto.cafe.common.models.LocationDetailModel;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 11/7/16.
 */
public class ClientServiceImplTest extends TestBase {


    @Autowired
    ClientService clientService;

    private ClientModel client;



    @Test
    public void testUpsertClient() throws Exception {

        LocationDetailModel ld = new LocationDetailModel();
        ld.setName("Main Office");

        AddressModel address = new AddressModel();
        address.setStreet1("12345");
        address.setStreet2("");
        address.setCity("Ogden");
        address.setState("UT");
        address.setZipCode(84604);
        address.setPhones(new HashSet<>(Arrays.asList("801.652.9154")));
        ld.setAddress(address);

        String name = "Automatic Car Credit2";
        client = new ClientModel();
        client.setId(UUID.randomUUID().toString());
        client.setName(name);
        client.setLocationDetails(ld);
        client.setHomePageText("");
        client.setCreatedUser("mcoletti");
        client.setCreated(DateTime.now().toString());
        client.setModifiedUser("mcoletti");
        client.setModified(DateTime.now().toString());
        client.setHomePageText("This is a Test");
        client.setLocationDetails(ld);
        clientService.upsertClient(client);

        client = clientService.getClient(client.getId(),false);
        assertThat(client).isNotNull();
        assertThat(client.getName()).isEqualTo(name);

    }

    @Test(dependsOnMethods = {"testUpsertClient"})
    public void testGetClientList() throws Exception {

        List<ClientModel> clientModels = clientService.getClientList();
        assertThat(clientModels).isNotNull();
        assertThat(clientModels.size()).isGreaterThan(0);
    }

}