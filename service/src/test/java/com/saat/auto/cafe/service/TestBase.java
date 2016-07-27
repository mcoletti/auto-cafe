package com.saat.auto.cafe.service;

import com.hazelcast.core.HazelcastInstance;
import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Location;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;
import com.saat.auto.cafe.data.DataBeans;

import org.joda.time.DateTime;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by micahcoletti on 7/27/16.
 */
@ContextConfiguration(classes = {ServiceBeans.class, DataBeans.class})
public class TestBase extends AbstractTestNGSpringContextTests {

    @Inject
    HazelcastInstance hazelcastInstance;

    public VehicleDetailsModel ROOT_VDM;

    static {
        System.setProperty("prop.path", TestBase.class.getClassLoader().getResource("app.properties").getPath());
    }


    /**
     * Helper Method to get a new vehicle object
     * @param vehicleId
     * @return
     */
    public void setROOT_VDM(String vehicleId){
        VehicleDetailsModel vd = VehicleDetailsModel.builder()
                .id(vehicleId)
                .clientId("395b2f0c-8008-410c-8402-fb64a3a7a295")
                .keyName("micah").stockNum(1234)
                .extColor("black").intColor("red")
                .trim("na").price(new BigDecimal(15500.00))
                .mileage("115,000").category("sedans")
                .make("Honda").model("Accord")
                .year(2003)
                .bodyStyle("sedan")
                .manufacture("Honda")
                .location(getLoc())
                .createdBy("testUser")
                .createdDtm(DateTime.now().toString())
                .modifiedBy("testUser")
                .modifiedDtm(DateTime.now().toString()).build();
        ROOT_VDM = vd;
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
