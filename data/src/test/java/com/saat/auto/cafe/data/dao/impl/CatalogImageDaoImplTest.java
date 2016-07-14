//package com.saat.auto.cafe.data.dao.impl;
//
//import com.saat.auto.cafe.common.interfaces.CatalogImagesDao;
//import com.saat.auto.cafe.common.models.CatalogImage;
//import com.saat.auto.cafe.data.DataBeans;
//import com.saat.auto.cafe.data.dao.DaoFactory;
//
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.util.Random;
//
//import javax.inject.Inject;
//import javax.sql.DataSource;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
///**
// * Created by mcoletti on 6/7/16.
// */
//@ContextConfiguration(classes = DataBeans.class)
//public class CatalogImageDaoImplTest extends AbstractTestNGSpringContextTests {
//
//    @Inject
//    private DataSource dataSource;
//
//    private CatalogImagesDao catalogImagesDao;
//
//    static {
//        System.setProperty("prop.path", VehicleDaoImplTest.class.getClassLoader().getResource("app.properties").getPath());
//    }
//
//    @BeforeClass
//    public void init() {
//
//        catalogImagesDao = DaoFactory.getCatalogImagesDao(dataSource);
//
//
//    }
//
//    @Test
//    public void testUpsertCatalogImages() throws Exception {
//
//        Random random = new Random();
//
//        CatalogImage ci = getCatalogImage(String.format("cdn-%s",1),1);
//
//        ci = catalogImagesDao.upsertCatalogImages(ci);
//        assertThat(ci).isNotNull();
//
//
//    }
//
//    @Test
//    public void testGet() throws Exception {
//
//    }
//
//
//
//    @Test
//    public void testGetCatalogImages() throws Exception {
//
//    }
//
//    private CatalogImage getCatalogImage(String cdnUrl,int veHicleId){
//        CatalogImage catalogImage = CatalogImage.builder()
//                .cdnUrl(cdnUrl).vehicleInventoryId(veHicleId)
//                .imageTypeId(1)
//                .build();
//        return catalogImage;
//    }
//
//
//}