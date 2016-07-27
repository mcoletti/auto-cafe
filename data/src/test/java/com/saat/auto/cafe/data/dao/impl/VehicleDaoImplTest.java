package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.entitys.VehicleImages;
import com.saat.auto.cafe.data.TestBase;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/18/16.
 */
public class VehicleDaoImplTest extends TestBase {


    private VehicleDao vehicleDao;
    private VehicleDetails VD_ROOT;
    private VehicleDetails VD_LOCAL;

    @BeforeClass
    public void init() {

        vehicleDao = new VehicleDaoImpl(cassandraInstance);

    }

    @Test
    public void testModolo(){



        String test = "julie.coletti@gmail.com";
        String test2 = "julie.coletti@gmail.com";
        int hash = Math.abs(test.hashCode());

        int hash2 = hash % 4;
       System.out.println(hash2);



    }

    @Test
    public void testUpsertVehicleDetails() throws Exception {

        setRootVD(UUID.randomUUID());
        VD_LOCAL = vehicleDao.upsertVehicleDetails(VD_ROOT);
        assertThat(VD_LOCAL).isNotNull();
        assertThat(VD_LOCAL.getId()).isEqualTo(VD_ROOT.getId());

    }

    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
    public void testGetByUUid() throws Exception {

        VD_LOCAL = vehicleDao.get(VD_ROOT.getId(),VD_ROOT.getClientId());
        assertThat(VD_LOCAL).isNotNull();
        assertThat(VD_LOCAL.getId()).isEqualTo(VD_ROOT.getId());
        assertThat(VD_LOCAL.getClientId()).isEqualTo(VD_ROOT.getClientId());
        assertThat(VD_LOCAL.getKeyName()).isEqualTo(VD_ROOT.getKeyName());
    }

    @Test
    public void testGetByKeyName() throws Exception {

    }

    @Test
    public void testGetByClientId() throws Exception {

    }

    @Test(dependsOnMethods = {"testUpsertVehicleDetails"})
    public void testInsertVehicleImage() throws Exception {

        VehicleImages vi = VehicleImages.builder()
                .vehicleId(VD_ROOT.getId())
                .imgName("test.png")
                .imgCdnLoc("/cdn/test.png")
                .imgSize(1024)
                .imgOrder(0)
                .imgSuffix(".png")
                .imgType("main").build();

        vi = vehicleDao.insertVehicleImage(vi);
        assertThat(vi).isNotNull();
    }

    @Test(dependsOnMethods = {"testInsertVehicleImage","testUpsertVehicleDetails"})
    public void testGetVehicleImageList() throws Exception {

        List<VehicleImages> imageList = vehicleDao.getVehicleImageList(VD_ROOT.getId());
        assertThat(imageList).isNotNull();
        assertThat(imageList.size()).isGreaterThan(0);
        assertThat(imageList.get(0).getVehicleId()).isEqualTo(VD_ROOT.getId());
    }

    private void setRootVD(UUID vehicleId) {

        VehicleDetails vd = VehicleDetails.builder()
                .id(vehicleId)
                .clientId(UUID.fromString("395b2f0c-8008-410c-8402-fb64a3a7a295"))
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
                .createdDtm(DateTime.now().toDate())
                .modifiedBy("testUser")
                .modifiedDtm(DateTime.now().toDate()).build();

        VD_ROOT = vd;
    }

}