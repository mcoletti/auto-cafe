package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.AddressModel;
import com.saat.auto.cafe.common.models.ContactModel;
import com.saat.auto.cafe.common.models.DealerShipModel;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 10/20/16.
 */
public class DealerShipServiceImplTest extends TestBase {



    @Autowired
    DealerShipService dealerShipService;

    private String clientId = "0a944635-f6dc-47e6-b570-8b59af2917b4";


    @Test
    public void testUpsertDealserShip() throws Exception {


        String dealerId = UUID.randomUUID().toString();
        String created = DateTime.now().toString();
        String modified = DateTime.now().toString();

        List<ContactModel> contacts = new ArrayList<>();

        List<String> phones = new ArrayList<>();
        phones.add("801.652.9154");
        AddressModel address = AddressModel.builder()
                .street1("12345").street2("12345")
                .city("Ogden").state("UT")
                .phones(phones).zipCode(84604).build();

        ContactModel contact = ContactModel.builder()
                .firstName("Ryan").lastName("Steed")
                .address(address).build();
        contacts.add(contact);

        contact = ContactModel.builder()
                .firstName("Matt").lastName("Steed")
                .address(address).build();
        contacts.add(contact);

        DealerShipModel dealerShip = new DealerShipModel();
        dealerShip.setId(dealerId);
        dealerShip.setClientId(clientId);
        dealerShip.setName("AUtomatic Car Credit");
        dealerShip.setCreatedUser("mcoletti");
        dealerShip.setCreatedDtm(created);
        dealerShip.setModifiedBy("mcoletti");
        dealerShip.setModifiedDtm(modified);
        dealerShipService.upsertDealserShip(dealerShip);

        dealerShip = dealerShipService.get(dealerShip.getId());

        assertThat(dealerShip).isNotNull();
        assertThat(dealerShip.getId()).isEqualTo(dealerId);
        assertThat(dealerShip.getClientId()).isEqualTo(clientId);
        assertThat(dealerShip.getCreatedDtm()).isEqualTo(created);
        assertThat(dealerShip.getModifiedDtm()).isEqualTo(modified);



        contact = ContactModel.builder()
                .firstName("Bob").lastName("Steed")
                .address(address).build();

        dealerShip.getContacts().add(contact);
        modified = DateTime.now().toString();
        dealerShip.setModifiedDtm(modified);

        dealerShipService.upsertDealserShip(dealerShip);
        dealerShip = dealerShipService.get(dealerId);
        assertThat(dealerShip).isNotNull();
        assertThat(dealerShip.getId()).isEqualTo(dealerId);
        assertThat(dealerShip.getClientId()).isEqualTo(clientId);
        assertThat(dealerShip.getContacts().size()).isEqualTo(3);
        assertThat(dealerShip.getCreatedDtm()).isEqualTo(created);
        assertThat(dealerShip.getModifiedDtm()).isEqualTo(modified);
    }
    @Test
    public void testGetDealerShips() throws Exception {


        List<DealerShipModel> dealerShipModels = dealerShipService.getDealerShips(clientId);

        assertThat(dealerShipModels).isNotNull();
        assertThat(dealerShipModels.size()).isGreaterThan(0);

    }
}