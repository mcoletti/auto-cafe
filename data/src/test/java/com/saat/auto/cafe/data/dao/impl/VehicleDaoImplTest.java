//package com.saat.auto.cafe.data.dao.impl;
//
//import com.saat.auto.cafe.common.interfaces.VehicleDao;
//import com.saat.auto.cafe.common.models.VehicleDetails;
//import com.saat.auto.cafe.data.DataBeans;
//import com.saat.auto.cafe.data.dao.DaoFactory;
//
//import org.joda.time.DateTime;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.math.BigDecimal;
//import java.util.Random;
//
//import javax.inject.Inject;
//import javax.sql.DataSource;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
///**
// * Created by mcoletti on 6/6/16.
// */
//@ContextConfiguration(classes = DataBeans.class)
//public class VehicleDaoImplTest extends AbstractTestNGSpringContextTests {
//
//
//    @Inject
//    private DataSource dataSource;
//
//    private VehicleDao vehicleDao;
//    private int vehicleId;
//    private String keyName;
//
//    static {
//        System.setProperty("prop.path", VehicleDaoImplTest.class.getClassLoader().getResource("app.properties").getPath());
//    }
//
//
//    @BeforeClass
//    public void init() {
//        vehicleDao = DaoFactory.getVehicleDao(dataSource);
//
//
//    }
//
//    @Test
//    public void testUpsertVehicle() throws Exception {
//
//        Random random = new Random();
//        VehicleDetails vi = getVehicleInventory(random.nextInt());
//
//        vi = vehicleDao.upsertVehicle(vi);
//        assertThat(vi).isNotNull();
//        assertThat(vi.getId()).isNotNull();
//
//        vi.setIntColor("black");
//        vi.setExtColor("brown");
//
//        vi = vehicleDao.upsertVehicle(vi);
//        assertThat(vi).isNotNull();
//        assertThat(vi.getId()).isNotNull();
//        assertThat(vi.getIntColor()).isEqualTo("black");
//        assertThat(vi.getExtColor()).isEqualTo("brown");
//        vehicleId = vi.getId();
//        keyName = vi.getKeyName();
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicle"})
//    public void testGet() throws Exception {
//
//        VehicleDetails vi = vehicleDao.get(vehicleId);
//        assertThat(vi).isNotNull();
//        assertThat(vi.getId()).isEqualTo(vehicleId);
//
//    }
//
//    @Test(dependsOnMethods = {"testUpsertVehicle"})
//    public void testGetByKeyName() throws Exception {
//        VehicleDetails vi = vehicleDao.get(keyName);
//        assertThat(vi).isNotNull();
//        assertThat(vi.getKeyName()).isEqualTo(keyName);
//    }
//
//    @Test
//    public void testGetCatalogImages() throws Exception {
//
//    }
//
//    @Test
//    public void testGetCatalogImages1() throws Exception {
//
//    }
//
//
//    private VehicleDetails getVehicleInventory(int id) {
//
//
//        VehicleDetails vi = VehicleDetails.builder()
//                .createdBy("testUser")
//                .createdDtm(DateTime.now())
//                .modifiedBy("testUserMod")
//                .modifiedDtm(DateTime.now())
//                .keyName(String.format("KeyName_%s", id))
//                .stockNum("12345")
//                .extColor("black")
//                .intColor("brown")
//                .trim("blue")
//                .vehiclePrice(new BigDecimal(60592))
//                .vehicleMileage("23,421")
//                .manufactureId(1)
//                .vehicleModelId(1)
//                .vehicleYearId(1)
//                .vehicleCategoryId(1)
//                .bodyStyleId(1)
//                .clientId(1)
//                .locationId(1).build();
//
//
//        return vi;
//
//    }
//
//}