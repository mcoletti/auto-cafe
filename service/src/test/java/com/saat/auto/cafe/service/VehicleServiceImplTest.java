package com.saat.auto.cafe.service;

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
        vehicleRoot = VehicleModel.builder()
                .dealerId("e9c6dfea-cc89-41ea-89f0-5ba5e2a0b816")
                .stockNum(UUID.randomUUID().toString().replace("-","").substring(0,5).toUpperCase())
                .vin("1C4AJWAG6EL295921")
                .options("4WD/AWD,ABS Brakes,Cargo Area Tiedowns,CD Player,Cruise Control,Driver Airbag,Electronic Brake Assistance,Fog Lights,Full Size Spare Tire,Locking Pickup Truck Tailgate,Passenger Airbag,Removable Top,Second Row Folding Seat,Second Row Removable Seat,Skid Plate,Steel Wheels,Steering Wheel Mounted Controls,Tachometer,Tilt Steering,Tilt Steering Column,Tire Pressure Monitor,Traction Control,Trip Computer,Vehicle Anti-Theft,Vehicle Stability Control System")
                .price(12000)
                .invoiceAmount(10000)
                .extColor("Black Clearcoat")
                .intColor("Black")
                .trim("")
                .bodyStyle("SPORT UTILITY 2-DR")
                .year(2013)
                .make("Honda")
                .model("Accord")
                .mileage(23000)
                .lotLocation("logan Utah")
                .createdUser("testUser")
                .createdDtm(DateTime.now().toString())
                .modifiedUser("testUser")
                .modifiedDtm(DateTime.now().toString()).build();
    }

}