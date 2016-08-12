package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.ClientVehicle;
import com.saat.auto.cafe.common.entitys.ClientVehicleCollection;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.entitys.VehicleDetail;
import com.saat.auto.cafe.data.TestBase;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/18/16.
 */
public class VehicleDaoImplTest extends TestBase {


    //    private VehicleDao vehicleDao;
    private VehicleDetail VD_ROOT;
    private VehicleDetail VD_LOCAL;

    private ClientVehicle ROOT_CV;
    private ClientVehicle LOCAL_CV;
    private UUID clientId;

    @Autowired
    VehicleDao vehicleDao;

    @BeforeClass
    public void init() {
        clientId = UUID.fromString("65026b3e-a833-4263-81a5-dedc2a6efed5");
    }


    @Test
    public void testUpsetClientVehicleNew() throws Exception {
        setRootCV();
        LOCAL_CV = vehicleDao.upsetClientVehicle(ROOT_CV);
        assertThat(LOCAL_CV).isNotNull();
        assertThat(LOCAL_CV.getClientId()).isEqualTo(ROOT_CV.getClientId());
        assertThat(LOCAL_CV.getVehicleId()).isEqualTo(ROOT_CV.getVehicleId());
        assertThat(LOCAL_CV.getStockNum()).isEqualTo(ROOT_CV.getStockNum());
//
//
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

        LOCAL_CV = vehicleDao.upsetClientVehicle(LOCAL_CV);
        assertThat(LOCAL_CV).isNotNull();

        assertThat(LOCAL_CV.getModifiedBy()).isEqualTo(modifiedUser);
        assertThat(LOCAL_CV.getDetails().getMake()).isEqualTo("honda");
        assertThat(LOCAL_CV.getClientId()).isEqualTo(ROOT_CV.getClientId());
        assertThat(LOCAL_CV.getVehicleId()).isEqualTo(ROOT_CV.getVehicleId());
        assertThat(LOCAL_CV.getStockNum()).isEqualTo(ROOT_CV.getStockNum());

    }

    @Test
    public void testUpsetClientVehicleUpdate() throws Exception {
        setRootCV();

        String updateUser = "testUser33";
        ROOT_CV.setModifiedBy(updateUser);


        LOCAL_CV = vehicleDao.upsetClientVehicle(ROOT_CV);
        assertThat(LOCAL_CV).isNotNull();
        assertThat(LOCAL_CV.getClientId()).isEqualTo(ROOT_CV.getClientId());
        assertThat(LOCAL_CV.getVehicleId()).isEqualTo(ROOT_CV.getVehicleId());
        assertThat(LOCAL_CV.getStockNum()).isEqualTo(ROOT_CV.getStockNum());


    }

    @Test(dependsOnMethods = {"testUpsetClientVehicleNew"})
    public void testGetByClientId() throws Exception {

        ClientVehicleCollection result = vehicleDao.get(ROOT_CV.getClientId());
        assertThat(result).isNotNull();
        assertThat(result.getClientVehicles()).isNotNull();
        assertThat(result.getClientVehicles().size()).isGreaterThan(0);
    }

    @Test(dependsOnMethods = {"testUpsetClientVehicleNew"})
    public void testGetByClientIdAndVehicleId() throws Exception {
        LOCAL_CV = vehicleDao.get(ROOT_CV.getClientId(), ROOT_CV.getVehicleId());
        assertThat(LOCAL_CV).isNotNull();
        assertThat(LOCAL_CV.getClientId()).isEqualTo(ROOT_CV.getClientId());
        assertThat(LOCAL_CV.getVehicleId()).isEqualTo(ROOT_CV.getVehicleId());
        assertThat(LOCAL_CV.getStockNum()).isEqualTo(ROOT_CV.getStockNum());
    }


    private ClientVehicle setRootCV() {

        DateTime createdDtm = DateTime.now();
        UUID timeUuid = UUIDGen.getTimeUUID(createdDtm.getMillis());

        ROOT_CV = new ClientVehicle();
        ROOT_CV.setClientId(clientId);
        ROOT_CV.setVehicleId(UUID.randomUUID());
        ROOT_CV.setStockNum(12345);
        ROOT_CV.setShortDesc("coolCar");
        ROOT_CV.setPrice(13499);
        ROOT_CV.setDetails(getVD());
        ROOT_CV.setLocation(getLoc());
        ROOT_CV.setCreatedBy("testUser");
        ROOT_CV.setCreatedDtm(DateTime.now().toDate());
        ROOT_CV.setModifiedBy("testUser");
        ROOT_CV.setModifiedDtm(DateTime.now().toDate());
//
//                .clientId(clientId).vehicleId(UUID.randomUUID())
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