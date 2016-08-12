package com.saat.auto.cafe.data;


import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by mcoletti on 6/1/16.
 */
@ContextConfiguration(classes = DataBeans.class)
public class TestBase extends AbstractTestNGSpringContextTests {



    @Autowired
    public CassandraInstance cassandraInstance;


    public Location getLoc(){

        Set<String> phones = new HashSet<>();
        phones.add("801.499.9683");
        Address a = new Address();
        a.setStreet1("1234");
        a.setStreet2("1234");
        a.setCity("provo");
        a.setState("ut");
        a.setZipCode(84604);
        a.setPhones(phones);

//                .street1("1234")
//                .street2("1234")
//                .city("provo")
//                .state("UT")
//                .zipCode(84604)
//                .phones(phones).build();

        Location l = new Location();
        l.setName("testLoc");
        l.setAddress(a);
//
//                .name("testLoc")
//                .address(address).build();
        return l ;

    }

}