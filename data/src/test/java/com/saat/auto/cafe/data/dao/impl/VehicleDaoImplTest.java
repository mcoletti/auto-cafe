package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.ImmType;
import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleCollection;
import com.saat.auto.cafe.common.entitys.VehicleImage;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.data.DataConfiguration;
import com.saat.auto.cafe.data.TestBase;
import com.saat.auto.cafe.data.dao.CassandraInstanceImpl;

import org.apache.cassandra.utils.UUIDGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/18/16.
 */
@ContextConfiguration(classes = DataConfiguration.class, loader = SpringApplicationContextLoader.class)
@ActiveProfiles("dev")
public class VehicleDaoImplTest extends AbstractTestNGSpringContextTests {


    private Vehicle vehicleRoot;
    private Vehicle vehicle;
    private UUID dealerId;

    // @Autowired
    // CassandraProperties cassandraProperties;
    // @Autowired
    // CassandraInstance cassandraInstance;
    @Autowired
    VehicleDao vehicleDao;

    @BeforeClass
    public void init() {
        //
        // cassandraInstance = new CassandraInstanceImpl(cassandraProperties);
        // vehicleDao = new VehicleDaoImpl(cassandraInstance);
        //
        dealerId = UUID.fromString("1dbcec8a-4dcd-49ce-af21-b4ed904ee416");
    }


    @Test
    public void testUpsetClientVehicleNew() throws Exception {
        setRootCV();
        vehicle = vehicleDao.upsert(vehicleRoot);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealershipId()).isEqualTo(vehicleRoot.getDealershipId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());

        String modifiedUser = "testUser2";
        vehicle.setModifiedUser(modifiedUser);


        vehicle = vehicleDao.upsert(vehicle);
        assertThat(vehicle).isNotNull();

        assertThat(vehicle.getModifiedUser()).isEqualTo(modifiedUser);
        assertThat(vehicle.getDealershipId()).isEqualTo(vehicleRoot.getDealershipId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());

    }


    @Test(dependsOnMethods = {"testUpsetClientVehicleNew"})
    public void testGetByDealerIdAndVehicleId() throws Exception {
        vehicle = vehicleDao.get(vehicleRoot.getDealershipId(), vehicleRoot.getStockNum());
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealershipId()).isEqualTo(vehicleRoot.getDealershipId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());
    }

    @Test(dependsOnMethods = {"testUpsetClientVehicleNew"})
    public void testInsertVehicleImage() throws Exception {

        VehicleImage vi = new VehicleImage();

        vi.setDealershipId(vehicleRoot.getDealershipId());
        vi.setStockNum(vehicleRoot.getStockNum());
        vi.setImgOrder(0);
        vi.setImgType(ImmType.Header.name());
        vi.setImgypeUrl("http://test/test/myImge.jpg");
        vi.setCreated(UUIDGen.getTimeUUID());

        vi = vehicleDao.insertVehicleImage(vi);
        assertThat(vi).isNotNull();
        assertThat(vi.getDealershipId()).isEqualTo(vehicleRoot.getDealershipId());
        assertThat(vi.getStockNum()).isEqualTo(vehicleRoot.getStockNum());
    }


    @Test(dependsOnMethods = {"testUpsetClientVehicleNew", "testInsertVehicleImage"})
    public void testGetByDealerId() throws Exception {

        List<Vehicle> vehicles = vehicleDao.get(vehicleRoot.getDealershipId());
        assertThat(vehicles).isNotNull();
        assertThat(vehicles.size()).isGreaterThan(0);

        vehicles.forEach(v -> {
            try {
                VehicleImage vi = vehicleDao.getVehicleImage(v.getDealershipId(), v.getStockNum());

                if (vi.getImgType().equals(ImmType.Header.name())) {
                    vi.setImgypeUrl(vi.getImgypeUrl());
                    vehicleDao.upsert(v);
                }

            } catch (ClientVehicleException e) {
                e.printStackTrace();
            }
        });
    }

    @Test(dependsOnMethods = {"testInsertVehicleImage", "testUpsetClientVehicleNew"})
    public void testGetVehicleImage() throws Exception {

        VehicleImage vi = vehicleDao.getVehicleImage(vehicleRoot.getDealershipId(), vehicleRoot.getStockNum());
        assertThat(vi).isNotNull();
        assertThat(vi.getDealershipId()).isEqualTo(vehicleRoot.getDealershipId());
        assertThat(vi.getStockNum()).isEqualTo(vehicleRoot.getStockNum());


    }


    private Vehicle setRootCV() {

        UUID timeUuid = UUIDGen.getTimeUUID();

        vehicleRoot = new Vehicle();
        vehicleRoot.setDealershipId(dealerId);
        vehicleRoot.setStockNum(UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase());
        vehicleRoot.setShortDescription("2001 Acura TL");
        vehicleRoot.setVin("1C4AJWAG6EL295921");
        vehicleRoot.setOptions("4WD/AWD,ABS Brakes,Cargo Area Tiedowns,CD Player,Cruise Control,Driver Airbag,Electronic Brake Assistance,Fog Lights,Full Size Spare Tire,Locking Pickup Truck Tailgate,Passenger Airbag,Removable Top,Second Row Folding Seat,Second Row Removable Seat,Skid Plate,Steel Wheels,Steering Wheel Mounted Controls,Tachometer,Tilt Steering,Tilt Steering Column,Tire Pressure Monitor,Traction Control,Trip Computer,Vehicle Anti-Theft,Vehicle Stability Control System");
        vehicleRoot.setPrice(13499);
        vehicleRoot.setInvoiceAmount(10000);
        vehicleRoot.setExtColor("red");
        vehicleRoot.setIntColor("black");
        vehicleRoot.setTrim("Sport 4WD");
        vehicleRoot.setBodyStyle("SEDAN 4-DR");
        vehicleRoot.setMake("honda");
        vehicleRoot.setModel("honda");
        vehicleRoot.setYear(2013);
        vehicleRoot.setLotId(12345);
        vehicleRoot.setLotLocation("Logan Utah Lot");
        vehicleRoot.setMileage(60000);
        vehicleRoot.setCreatedUser("testUser");
        vehicleRoot.setCreated(timeUuid);
        vehicleRoot.setModifiedUser("testUser");
        vehicleRoot.setModified(timeUuid);
        return vehicleRoot;


    }


}