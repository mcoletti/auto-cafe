package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.common.interfaces.services.VehicleService;
import com.saat.auto.cafe.common.models.VehicleModel;
import com.saat.auto.cafe.common.models.VehicleModelCollection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/27/16.
 */
public class VehicleServiceImplTest extends TestBase {



    @Autowired
    VehicleService vehicleService;

    public VehicleModel vehicleRoot;

    @Test
    public void testUpsertDealerVehicle() throws Exception {

        createVehicleRoot();
        vehicleService.upsertDealerShipVehicle(vehicleRoot);
        VehicleModel vehicle = vehicleService.get(vehicleRoot.getDealerId(),vehicleRoot.getStockNum(),true);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealerId()).isEqualTo(vehicleRoot.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());



        vehicle.setModifiedUser("micah");
        vehicle.setModifiedDtm(DateTime.now().toString());
        vehicle.setPrice(13500);
        vehicle.setPrice(24500);

        vehicleService.upsertDealerShipVehicle(vehicle);
        vehicle = vehicleService.get(vehicle.getDealerId(),vehicle.getStockNum());
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getModifiedUser()).isEqualTo("micah");
        assertThat(vehicle.getDealerId()).isEqualTo(vehicleRoot.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());




    }

    @Test(dependsOnMethods = {"testUpsertDealerVehicle"})
    public void testGetByDealerId() throws Exception {

        List<VehicleModel> vehicleModels = vehicleService.get(vehicleRoot.getDealerId());
        assertThat(vehicleModels).isNotNull();
        assertThat(vehicleModels.size()).isGreaterThan(0);
    }

    @Test(dependsOnMethods = {"testUpsertDealerVehicle"})
    public void testGetByDealerIdAndStockNum() throws Exception {
        VehicleModel vehicle = vehicleService.get(vehicleRoot.getDealerId(), vehicleRoot.getStockNum());
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealerId()).isEqualTo(vehicleRoot.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());
    }
    public void createVehicleRoot() {
        vehicleRoot = new VehicleModel();
        vehicleRoot.setDealerId("e9c6dfea-cc89-41ea-89f0-5ba5e2a0b816");
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
        vehicleRoot.setLotId(12345);
        vehicleRoot.setLotLocation("Logan Utah Lot");
        vehicleRoot.setMileage(60000);
        vehicleRoot.setCreatedUser("testUser");
        vehicleRoot.setCreatedDtm(DateTime.now().toString());
        vehicleRoot.setModifiedUser("testUser");
        vehicleRoot.setModifiedDtm(DateTime.now().toString());

    }

}