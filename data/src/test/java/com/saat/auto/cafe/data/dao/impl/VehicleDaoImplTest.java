package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleCollection;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.entitys.VehicleDetail;
import com.saat.auto.cafe.data.TestBase;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/18/16.
 */
public class VehicleDaoImplTest extends TestBase {


    //    private VehicleDao vehicleDao;
    private VehicleDetail VD_ROOT;
    private VehicleDetail VD_LOCAL;

    private Vehicle ROOT_CV;
    private Vehicle LOCAL_CV;
    private UUID dealerId;

    @Autowired
    VehicleDao vehicleDao;

    @BeforeClass
    public void init() {
        dealerId = UUID.fromString("ad44d405-8240-4035-98c9-2b9b528b2e86");
    }


    @Test
    public void testUpsetClientVehicleNew() throws Exception {
        setRootCV();
        LOCAL_CV = vehicleDao.upsertClientVehicle(ROOT_CV);
        assertThat(LOCAL_CV).isNotNull();
        assertThat(LOCAL_CV.getDealerId()).isEqualTo(ROOT_CV.getDealerId());
        assertThat(LOCAL_CV.getStockNum()).isEqualTo(ROOT_CV.getStockNum());

        String modifiedUser = "testUser2";
        LOCAL_CV.setModifiedBy(modifiedUser);

        VehicleDetail vehicleDetail = new VehicleDetail();
        vehicleDetail.setBodyStyle("sedan");
        vehicleDetail.setExtColor("blue");
        vehicleDetail.setIntColor("brown");
        vehicleDetail.setMake("honda");
        vehicleDetail.setModel("accord");
        vehicleDetail.setTrim("stuff");
        vehicleDetail.setMileage(60000);
        vehicleDetail.setYear(2015);

        LOCAL_CV.setDetails(vehicleDetail);

        LOCAL_CV = vehicleDao.upsertClientVehicle(LOCAL_CV);
        assertThat(LOCAL_CV).isNotNull();

        assertThat(LOCAL_CV.getModifiedBy()).isEqualTo(modifiedUser);
        assertThat(LOCAL_CV.getDetails().getMake()).isEqualTo("honda");
        assertThat(LOCAL_CV.getDealerId()).isEqualTo(ROOT_CV.getDealerId());
        assertThat(LOCAL_CV.getStockNum()).isEqualTo(ROOT_CV.getStockNum());

    }



    @Test(dependsOnMethods = {"testUpsetClientVehicleNew"})
    public void testGetByDealerId() throws Exception {

        VehicleCollection result = vehicleDao.get(ROOT_CV.getDealerId());
        assertThat(result).isNotNull();
        assertThat(result.getVehicles()).isNotNull();
        assertThat(result.getVehicles().size()).isGreaterThan(0);
    }

    @Test(dependsOnMethods = {"testUpsetClientVehicleNew"})
    public void testGetByDealerIdAndVehicleId() throws Exception {
        LOCAL_CV = vehicleDao.get(ROOT_CV.getDealerId(), ROOT_CV.getStockNum());
        assertThat(LOCAL_CV).isNotNull();
        assertThat(LOCAL_CV.getDealerId()).isEqualTo(ROOT_CV.getDealerId());
        assertThat(LOCAL_CV.getStockNum()).isEqualTo(ROOT_CV.getStockNum());
    }


    private Vehicle setRootCV() {

        Random random=new Random();
        int randomNumber=(random.nextInt(65536)-32768);

        DateTime createdDtm = DateTime.now();
        UUID timeUuid = UUIDGen.getTimeUUID(createdDtm.getMillis());

        ROOT_CV = new Vehicle();
        ROOT_CV.setDealerId(dealerId);
        ROOT_CV.setStockNum(UUID.randomUUID().toString().replace("-","").substring(0,5).toUpperCase());
        ROOT_CV.setShortDesc("coolCar");
        ROOT_CV.setDescription("Has CD/DVD player and Sunroof and cool stuff");
        ROOT_CV.setPrice(13499);
        ROOT_CV.setDetails(getVD());
        ROOT_CV.setLocation(getLoc());
        ROOT_CV.setCreatedBy("testUser");
        ROOT_CV.setCreatedDtm(DateTime.now().toDate());
        ROOT_CV.setModifiedBy("testUser");
        ROOT_CV.setModifiedDtm(DateTime.now().toDate());
//
//                .dealerId(dealerId).vehicleId(UUID.randomUUID())
//                .stockNum(12345)
//                .shortDesc("Cool Car")
//                .price(13499)
//                .details(getVD()).createdBy("testUser")
//                .location(getLoc())
//                .createdDtm(createdDtm)
//                .modifiedBy("testUser").modifiedDtm(DateTime.now()).build();

        return ROOT_CV;


    }

    private VehicleDetail getVD() {

        VehicleDetail vd = new VehicleDetail();
        vd.setBodyStyle("sedan");
        vd.setExtColor("red");
        vd.setIntColor("black");
        vd.setMake("ford");
        vd.setModel("focus");
        vd.setTrim("stuff");
        vd.setMileage(60000);
        vd.setYear(2013);
        return vd;


    }
}