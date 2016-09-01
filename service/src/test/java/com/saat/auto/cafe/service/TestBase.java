package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.HazelCastService;
import com.saat.auto.cafe.common.models.AddressModel;
import com.saat.auto.cafe.common.models.VehicleModel;
import com.saat.auto.cafe.common.models.LocationModel;
import com.saat.auto.cafe.common.models.VehicleDetailModel;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.saat.auto.cafe.data.DataBeans;

/**
 * Created by micahcoletti on 7/27/16.
 */
@ContextConfiguration(classes = {ServiceBeans.class, DataBeans.class})
public class TestBase extends AbstractTestNGSpringContextTests {


    @Autowired
    HazelCastService hazelCastService;

    @Autowired
    CacheService cacheService;

    public VehicleModel ROOT_DVM;


    public void setROOT_DVM() {
        ROOT_DVM = VehicleModel.builder()
                .dealerId("ad44d405-8240-4035-98c9-2b9b528b2e86")
                .stockNum(UUID.randomUUID().toString().replace("-","").substring(0,5).toUpperCase())
                .createdBy("testUser")
                .createdDtm(DateTime.now().toString())
                .modifiedBy("testUser")
                .modifiedDtm(DateTime.now().toString())
                .details(getDetails())
                .location(getLocation())
                .price(66500)
                .shortDesc("Cool Car")
                .description("This is a really cool car")
                .build();
    }

    private LocationModel getLocation() {
        return LocationModel.builder()
                .name("ogden")
                .address(getAddress()).build();
    }

    private AddressModel getAddress() {
        List<String> phonesList = new ArrayList<>();
        phonesList.add("801.499.9683");
        return AddressModel.builder()
                .street1("12345")
                .street2("12345")
                .city("provo")
                .state("UT")
                .phones(phonesList)
                .zipCode(84604).build();
    }

    private VehicleDetailModel getDetails() {
        return VehicleDetailModel.builder()
                .bodyStyle("sedan")
                .extColor("red")
                .intColor("black")
                .make("ford")
                .model("focus")
                .mileage(66000)
                .year(2013)
                .build();
    }
}
