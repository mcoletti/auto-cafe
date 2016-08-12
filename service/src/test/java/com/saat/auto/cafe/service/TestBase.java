package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Location;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.HazelCastService;
import com.saat.auto.cafe.common.models.AddressModel;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;
import com.saat.auto.cafe.common.models.LocationModel;
import com.saat.auto.cafe.common.models.VehicleDetailModel;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
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

    public ClientVehiclesModel ROOT_CVM;


    public void setROOT_CVM() {

        ROOT_CVM = ClientVehiclesModel.builder()
                .clientId("")
                .vehicleId(UUID.randomUUID().toString())
                .createdBy("testUser")
                .createdDtm(DateTime.now().toString())
                .modifiedBy("testUser")
                .modifiedDtm(DateTime.now().toString())
                .details(getDetails())
                .location(getLocation())
                .price(66500)
                .shortDesc("Cool Car")
                .stockNum(123456789)
                .build();
    }

    private LocationModel getLocation() {
        return LocationModel.builder()
                .name("ogden")
                .address(getAddress()).build();
    }

    private AddressModel getAddress() {
        return AddressModel.builder()
                .street1("12345")
                .street2("12345")
                .city("provo")
                .state("UT")
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
