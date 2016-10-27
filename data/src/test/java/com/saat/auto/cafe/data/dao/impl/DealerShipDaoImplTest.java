package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Contact;
import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.entitys.DealershipLot;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.data.TestBase;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        String dealerName = "testDealer";
        UUID clientId = UUID.fromString("1daa3920-c111-43bc-9dc3-a412e133688f");
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