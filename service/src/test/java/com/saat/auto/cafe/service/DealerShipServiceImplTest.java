package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.entitys.Contact;
import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.AddressModel;
import com.saat.auto.cafe.common.models.ContactModel;
import com.saat.auto.cafe.common.models.DealerShipModel;
import com.saat.auto.cafe.service.TestBase;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

/**
 * Created by micahcoletti on 10/20/16.
 */
public class DealerShipServiceImplTest extends TestBase {


    @Autowired
    DealerShipService dealerShipService;



    @Test
    public void testUpsertDealserShip() throws Exception {


        String dealerId = UUID.randomUUID().toString();
        String clientId = "0a944635-f6dc-47e6-b570-8b59af2917b4";
        List<ContactModel> contacts = new ArrayList<>();

        List<String> phones = new ArrayList<>();
        phones.add("801.652.9154");
        AddressModel address = AddressModel.builder()
                .street1("12345").street2("12345")
                .city("Ogden").state("UT")
                .phones(phones).build();

        ContactModel contact = ContactModel.builder()
                .firstName("Ryan").lastName("Steed")
                .address(address).build();
        contacts.add(contact);

        contact = ContactModel.builder()
                .firstName("Matt").lastName("Steed")
                .address(address).build();
        contacts.add(contact);
        DealerShipModel model = DealerShipModel.builder()
                .id(dealerId)
                .clientId(clientId)
                .name("Automatic Car Credit")
                .contacts(contacts)
                .createdUser("mcoletti")
                .createdDtm(DateTime.now().toString())
                .modifiedBy("mcoletti")
                .modifiedDtm(DateTime.now().toString()).build();

        dealerShipService.upsertDealserShip(model);

        model = dealerShipService.get(model.getId());

        assertThat(model).isNotNull();
        assertThat(model.getId()).isEqualTo(dealerId);
        assertThat(model.getClientId()).isEqualTo(clientId);





    }

}