package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleCollection;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.data.TestBase;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/18/16.
 */
public class VehicleDaoImplTest extends TestBase {



    private Vehicle vehicleRoot;
    private Vehicle vehicle;
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
    public void testGetByDealerId() throws Exception {

        VehicleCollection result = vehicleDao.get(vehicleRoot.getDealershipId());
        assertThat(result).isNotNull();
        assertThat(result.getVehicles()).isNotNull();
        assertThat(result.getVehicles().size()).isGreaterThan(0);
    }

    @Test(dependsOnMethods = {"testUpsetClientVehicleNew"})
    public void testGetByDealerIdAndVehicleId() throws Exception {
        vehicle = vehicleDao.get(vehicleRoot.getDealershipId(), vehicleRoot.getStockNum());
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealershipId()).isEqualTo(vehicleRoot.getDealershipId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());
    }


    private Vehicle setRootCV() {

        DateTime createdDtm = DateTime.now();
        UUID timeUuid = UUIDGen.getTimeUUID(createdDtm.getMillis());

        vehicleRoot = new Vehicle();
        vehicleRoot.setDealershipId(dealerId);
        vehicleRoot.setStockNum(UUID.randomUUID().toString().replace("-","").substring(0,5).toUpperCase());
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
        vehicleRoot.setLotLocation("Logan Utah Lot");
        vehicleRoot.setMileage(60000);
        vehicleRoot.setCreatedUser("testUser");
        vehicleRoot.setCreated(timeUuid);
        vehicleRoot.setModifiedUser("testUser");
        vehicleRoot.setModified(timeUuid);
        return vehicleRoot;


    }


}