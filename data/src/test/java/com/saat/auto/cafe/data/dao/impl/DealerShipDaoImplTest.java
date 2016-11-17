package com.saat.auto.cafe.data.dao.impl;

import com.beust.jcommander.internal.Lists;
import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Contact;
import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.entitys.DealershipLot;
import com.saat.auto.cafe.common.entitys.LocationDetail;
import com.saat.auto.cafe.common.entitys.MakeVehicleTotal;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.data.TestBase;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
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
 * Created by micahcoletti on 7/12/16.
 */
public class DealerShipDaoImplTest extends TestBase {



    private UUID dealerId;
    @Autowired
    private DealerShipDao dealerShipDao;

    @BeforeClass
    public void init(){

//        dealerShipDao = new DealerShipDaoImpl(cassandraInstance);

    }



    @Test
    public void testUpsert() throws Exception {

        // Test Add New

        String user = "testUser";
        String dealerName = "ACC Logan";
        UUID clientId = UUID.fromString("66f5edef-db30-4416-b543-a46e516a982e");
        dealerId = UUID.randomUUID();
        UUID timeUuid = UUIDGen.getTimeUUID();


        Address address = new Address();
        address.setStreet1("12345");
        address.setStreet2("12345");
        address.setZipCode(84604);
        address.setState("UT");
        Set<String> phones = new HashSet<>();
        phones.add("801.652.9154");
        address.setPhones(phones);

        Contact contact = new Contact();
        contact.setFirstName("Ryan");
        contact.setLastName("Steed");
        contact.setAddress(address);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);

        DealerShip dealerShip = new DealerShip();
        dealerShip.setId(dealerId);
        dealerShip.setClientId(clientId);
        dealerShip.setName(dealerName);
        dealerShip.setCreatedUser(user);
        dealerShip.setCreated(timeUuid);
        dealerShip.setModifiedUser(user);
        dealerShip.setModified(timeUuid);
        dealerShip.setContacts(contacts);
        dealerShip.setHeaderTitle("Automatic Care Credit of Logan");
        dealerShip.setHomeWelcomeMessage("Auto Car Credit of Logan is dedicated to providing the ultimate automobile buying experience. Auto Car Credit of Ogden is your #1 source for buying " +
                "a quality pre-owned vehicle at wholesale price. We have extensive relationships in the dealer community allowing us to purchase a wide variety of " +
                "lease returns and new car trades at exceptional values. This enables Auto Car Credit of Ogden to pass along huge savings on the highest quality vehicles of your choice\n" +
                "In Addition , we offer a full array of financing options to meet your needs. At our website, you can take advantage of several Internet technologies in the comfort of your home. " +
                "Remember, if you need to talk to us, we are only a phone call away. Our departments (Sales, Services, and the Business office) are available to help you with all your automobile needs. " +
                "Feel free to come by the store at any time to meet us in person. We invite you to take a tour of our facility and enjoy a pressure free car buying experience.");
        dealerShip.setHeaderImgUrl("/img001.jpg");

        // Setup the MakeVehicle Totals
        Map<String,Integer> makeVehicleTotals = new HashMap<>();
        makeVehicleTotals.put("Acura",10);
        makeVehicleTotals.put("Honda",10);
        makeVehicleTotals.put("BMW",10);
        dealerShip.setMakeVehicleTotals(makeVehicleTotals);

        // Set the Location Detail
        LocationDetail ld = new LocationDetail();
        ld.setName("Ogden DealerShip");
        address = new Address();
        address.setStreet1("362 Wall Ave");
        address.setStreet2("");
        address.setCity("Ogden");
        address.setZipCode(84405);
        address.setState("UT");
        phones = new HashSet<>();
        phones.add("(801) 392-0039");
        address.setPhones(phones);
        ld.setAddress(address);
        // Add the Location Detail
        dealerShip.setLocationDetail(ld);

//                .id(dealershipId)
//                .createdUser(user)
//                .created(DateTime.now().toDate())
//                .clientName("testClient")
//                .modifiedUser("testUser")
//                .modified(DateTime.now().toDate())
//                .locations(locationMap).build();


        dealerShipDao.upsert(dealerShip);
        dealerShip = dealerShipDao.get(dealerShip.getId());

        assertThat(dealerShip).isNotNull();
        assertThat(dealerShip.getId()).isEqualTo(dealerId);

        // Test Update
        user = "testUser2";
        DateTime updateDtm = DateTime.now();
        dealerShip.setModifiedUser(user);
        dealerShip.setModified(UUIDGen.getTimeUUID());
        contact = new Contact();
        contact.setFirstName("Matt");
        contact.setLastName("Steed");
        contact.setAddress(address);
        contacts = dealerShip.getContacts();
        contacts.add(contact);



        dealerShipDao.upsert(dealerShip);
        dealerShip = dealerShipDao.get(dealerShip.getId());
        assertThat(dealerShip).isNotNull();
        assertThat(dealerShip.getModifiedUser()).isEqualTo(user);
    }

    @Test
    public void updateDealerShip() throws Exception{





    }


    @Test(dependsOnMethods = {"testUpsert"})
    public void testGetDealerships() throws Exception {
        UUID clientId = UUID.fromString("0a944635-f6dc-47e6-b570-8b59af2917b4");
        List<DealerShip> dealerShips = dealerShipDao.getDealerships(clientId);

        assertThat(dealerShips).isNotNull();
        assertThat(dealerShips.size()).isGreaterThan(0);

    }

    @Test
    public void testUpsertDealerShipLot() throws Exception {

    }

    @Test
    public void testGetDealershipLots() throws Exception {


        List<DealershipLot> dealershipLots = dealerShipDao.getDealershipLots(UUID.fromString("c1ee6433-e002-4563-9c7a-604c75eeef86"));
        assertThat(dealershipLots).isNotNull();

    }



}