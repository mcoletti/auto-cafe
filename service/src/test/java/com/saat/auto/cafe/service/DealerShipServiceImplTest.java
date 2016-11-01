package com.saat.auto.cafe.service;

import com.beust.jcommander.internal.Lists;
import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.AddressModel;
import com.saat.auto.cafe.common.models.ContactModel;
import com.saat.auto.cafe.common.models.DealerShipModel;
import com.saat.auto.cafe.common.models.LocationDetailModel;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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



        AddressModel address = new AddressModel();
        address.setStreet1("12345");
        address.setStreet2("");
        address.setZipCode(84604);
        address.setState("UT");
        Set<String> phones = new HashSet<>();
        phones.add("801.652.9154");
        address.setPhones(phones);

        ContactModel contact = new ContactModel();
        contact.setFirstName("Ryan");
        contact.setLastName("Steed");
        contact.setAddress(address);

        List<ContactModel> contacts = new ArrayList<>();
        contacts.add(contact);

        DealerShipModel dealerShip = new DealerShipModel();
        dealerShip.setId(dealerId);
        dealerShip.setClientId(clientId);
        dealerShip.setName("AUtomatic Car Credit");
        dealerShip.setContacts(contacts);
        dealerShip.setPageTitleHeader("Automatic Care Credit of Ogden");
        dealerShip.setImgHeaderLogos(Lists.newArrayList("image1","image2"));
        // setup location detail
        LocationDetailModel ldm = new LocationDetailModel();
        ldm.setName("Ogden DealerShip");
        address = new AddressModel();
        address.setStreet1("362 Wall Ave");
        address.setStreet2("");
        address.setCity("Ogden");
        address.setZipCode(84405);
        address.setState("UT");
        phones = new HashSet<>();
        phones.add("(801) 392-0039");
        address.setPhones(phones);
        ldm.setAddress(address);

        dealerShip.setLocationDetail(ldm);
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

        // Update some data
        contact = new ContactModel();
        contact.setFirstName("Bob");
        contact.setLastName("Steed");
        contact.setAddress(address);

        dealerShip.getContacts().add(contact);
        modified = DateTime.now().toString();
        dealerShip.setModifiedDtm(modified);

        dealerShipService.upsertDealserShip(dealerShip);
        dealerShip = dealerShipService.get(dealerId);
        assertThat(dealerShip).isNotNull();
        assertThat(dealerShip.getId()).isEqualTo(dealerId);
        assertThat(dealerShip.getClientId()).isEqualTo(clientId);
        assertThat(dealerShip.getContacts().size()).isEqualTo(2);
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