package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.AddressModel;
import com.saat.auto.cafe.common.models.ContactModel;
import com.saat.auto.cafe.common.models.DealerShipModel;
import com.saat.auto.cafe.common.models.LocationDetailModel;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 10/20/16.
 */
public class DealerShipServiceImplTest extends TestBase {



    @Autowired
    DealerShipService dealerShipService;

    private String clientId = "ad92e832-9830-49a5-98b5-9f9365fd20bd";


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
        dealerShip.setName("Automatic Car Credit: Logan");
        dealerShip.setContacts(contacts);
        dealerShip.setHeaderTitle("Automatic Care Credit of Logan");
        dealerShip.setHeaderImgUrl("/image2.jpg");
        dealerShip.setHomeWelcomeMessage("Auto Car Credit of Ogden is dedicated to providing the ultimate automobile buying experience. Auto Car Credit of Ogden is your #1 source for buying " +
                "a quality pre-owned vehicle at wholesale price. We have extensive relationships in the dealer community allowing us to purchase a wide variety of " +
                "lease returns and new car trades at exceptional values. This enables Auto Car Credit of Ogden to pass along huge savings on the highest quality vehicles of your choice\n" +
                "In Addition , we offer a full array of financing options to meet your needs. At our website, you can take advantage of several Internet technologies in the comfort of your home. " +
                "Remember, if you need to talk to us, we are only a phone call away. Our departments (Sales, Services, and the Business office) are available to help you with all your automobile needs. " +
                "Feel free to come by the store at any time to meet us in person. We invite you to take a tour of our facility and enjoy a pressure free car buying experience.");
        Map<String,Integer> makeVehicleTotals = new HashMap<>();
        makeVehicleTotals.put("Acura",10);
        makeVehicleTotals.put("Honda",10);
        makeVehicleTotals.put("BMW",10);
        dealerShip.setMakeVehicleTotals(makeVehicleTotals);

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


        // List<DealerShipModel> dealerShipModels = dealerShipService.getDealerShips(clientId);
        // int currentSize = dealerShipModels.size();
        //
        // modified = DateTime.now().toString();
        //
        // dealerShip.setModifiedDtm(modified);
        // dealerShipService.upsertDealserShip(dealerShip);
        // dealerShip = dealerShipService.get(dealerId);
        // assertThat(dealerShip).isNotNull();
        // assertThat(dealerShip.getId()).isEqualTo(dealerId);
        // assertThat(dealerShip.getClientId()).isEqualTo(clientId);
        // assertThat(dealerShip.getContacts().size()).isEqualTo(2);
        // assertThat(dealerShip.getCreatedDtm()).isEqualTo(created);
        // assertThat(dealerShip.getModifiedDtm()).isEqualTo(modified);
        //
        // dealerShipModels = dealerShipService.getDealerShips(clientId);
        // int newSize = dealerShipModels.size();
        // assertThat(dealerShipModels).isNotNull();
        // assertThat(newSize).isGreaterThan(currentSize);
        //
        // dealerShipModels = dealerShipService.getDealerShips(clientId);
        // assertThat(dealerShipModels).isNotNull();
        // assertThat(dealerShipModels.size()).isEqualTo(newSize);


    }
    @Test
    public void testGetDealerShips() throws Exception {


        List<DealerShipModel> dealerShipModels = dealerShipService.getDealerShips(clientId);

        assertThat(dealerShipModels).isNotNull();
        assertThat(dealerShipModels.size()).isGreaterThan(0);

    }
}