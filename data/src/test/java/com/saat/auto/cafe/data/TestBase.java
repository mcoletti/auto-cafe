package com.saat.auto.cafe.data;

// import com.datastax.driver.core.Cluster;
// import com.datastax.driver.core.ResultSet;
// import com.datastax.driver.core.Session;

import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.models.Address;
import com.saat.auto.cafe.common.models.Location;
import com.saat.auto.cafe.data.dao.impl.ClientsDaoImplTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by mcoletti on 6/1/16.
 */
@ContextConfiguration(classes = DataBeans.class)
public class TestBase extends AbstractTestNGSpringContextTests {

    @Inject
    public CassandraInstance cassandraInstance;

    static {
        System.setProperty("prop.path", ClientsDaoImplTest.class.getClassLoader().getResource("app.properties").getPath());
    }

    public Location getLoc(){

        Location location = new Location();
        location.setName("testLoc");

        Address address = new Address();
        address.setStreet1("1234");
        address.setStreet2("1234");
        Set<String> phones = new HashSet<>();
        phones.add("801.499.9683");
        address.setPhones(phones);
        address.setCity("provo");
        address.setState("UT");
        address.setZipCode(84604);

        location.setAddress(address);

        return location;

    }

}