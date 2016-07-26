// package com.saat.auto.cafe.data.dao.impl;
//
// import com.saat.auto.cafe.common.exceptions.VehicleDetailsException;
// import com.saat.auto.cafe.common.interfaces.VehicleDao;
// import com.saat.auto.cafe.common.models.CatalogImage;
// import com.saat.auto.cafe.common.models.VehicleDetailsModel;
//
// import org.mockito.Mock;
// import org.testng.Reporter;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.Test;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;
//
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.when;
// import static org.mockito.MockitoAnnotations.initMocks;
//
// /**
//  * Created by mcoletti on 6/1/16.
//  */
// // @ContextConfiguration(classes = {Beans.class})
// public class VehicleDaoImplUnitTest {
//
//
//     private List<CatalogImage> ciList;
//     private List<CatalogImage> ciList2;
//     private VehicleDetailsModel vi1;
//     private VehicleDetailsModel vi2;
//     private int vehicleId1;
//     private int vehicleId2;
//
//
//     @Mock
//     VehicleDao vehicleDao;
//
//
//     @BeforeClass
//     public void init() throws VehicleDetailsException {
//         Reporter.log("#### Setting up Vehicle Unit Test ####",true);
//         initMocks(this);
//
//         Random random = new Random();
//         vehicleId1 = random.nextInt();
//         vi1 = VehicleDetailsModel.builder().build();
//         vi1.setId(vehicleId1);
//         vi1.setKeyName(String.format("testKeyName-%s", vehicleId1));
//         CatalogImage ci = getCi(vi1.getId());
//
//         ciList = new ArrayList<>();
//         ciList.add(ci);
//
//         ciList2 = new ArrayList<>();
//         vehicleId2 = random.nextInt();
//         vi2 = VehicleDetailsModel.builder().build();
//         vi2.setId(vehicleId2);
//         vi2.setKeyName(String.format("testKeyName-%s", vehicleId2));
//         ci = getCi(vi2.getId());
//         ciList2.add(ci);
//
//         /** Setup getting Catalog Images **/
//         // By Name
//         when(vehicleDao.getCatalogImages(String.format("testKeyName-%s", vehicleId1))).thenReturn(ciList);
//         when(vehicleDao.getCatalogImages(String.format("testKeyName-%s", vehicleId2))).thenReturn(ciList2);
//         // By Id
//         when(vehicleDao.getCatalogImages(vehicleId1)).thenReturn(ciList);
//         when(vehicleDao.getCatalogImages(vehicleId2)).thenReturn(ciList2);
//
//         /**
//          * Setup for getting Vehicle Inventory Objects
//          */
//         when(vehicleDao.get(vehicleId1)).thenReturn(vi1);
//         when(vehicleDao.get(vehicleId2)).thenReturn(vi2);
//
//
//     }
//
//
//     @BeforeMethod
//     public void beforeMethod() {
//
//
//     }
//
//
//     @Test
//     public void testGetCatalogImagesById() throws Exception {
//         Reporter.log("#### Test Get Catalog Images by Id ####",true);
//         List<CatalogImage> result = vehicleDao.getCatalogImages(vehicleId1);
//         assertThat(result).isNotNull();
//         assertThat(result.get(0).getVehicleInventoryId()).isEqualTo(vehicleId1);
//
//         result = vehicleDao.getCatalogImages(vehicleId2);
//         assertThat(result).isNotNull();
//         assertThat(result.get(0).getVehicleInventoryId()).isEqualTo(vehicleId2);
//
//         // verify(vehicleDao);
//     }
//
//     @Test()
//     public void testGetCatalogImagesKeyName() throws Exception {
//
//         String name = String.format("testKeyName-%s", vehicleId1);
//         List<CatalogImage> result = vehicleDao.getCatalogImages(name);
//         assertThat(result).isNotNull();
//         assertThat(result.get(0).getVehicleInventoryId()).isEqualTo(vehicleId1);
//
//         name = String.format("testKeyName-%s", vehicleId2);
//         result = vehicleDao.getCatalogImages(name);
//         assertThat(result).isNotNull();
//         assertThat(result.get(0).getVehicleInventoryId()).isEqualTo(vehicleId2);
//
//         // verify(vehicleDao);
//     }
//
//     @Test
//     public void testGetVehicleInventoryById() throws Exception {
//
//         VehicleDetailsModel result = vehicleDao.get(vehicleId1);
//         assertThat(result).isNotNull();
//         assertThat(result).isEqualTo(vi1);
//
//         result = vehicleDao.get(vehicleId2);
//         assertThat(result).isNotNull();
//         assertThat(result).isEqualTo(vi2);
//
//         // verify(vehicleDao);
//
//     }
//
//     @Test
//     public void testGetVehicleInventoryByKeyName() throws Exception {
//
//     }
//
//     private CatalogImage getCi(int vehicleId) {
//         // CatalogImage ci = new CatalogImage
//         return null;
//     }
//
// }