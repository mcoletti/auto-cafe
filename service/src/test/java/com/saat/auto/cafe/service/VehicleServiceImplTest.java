package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.common.interfaces.services.VehicleService;
import com.saat.auto.cafe.common.models.VehicleModel;
import com.saat.auto.cafe.common.models.VehicleModelCollection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by micahcoletti on 7/27/16.
 */
public class VehicleServiceImplTest extends TestBase {



    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    VehicleService vehicleService;

    public VehicleModel vehicleRoot;

    @Test
    public void testUpsertDealerVehicle() throws Exception {

        createVehicleRoot();
        VehicleModel vehicle = vehicleService.upsertDealerShipVehicle(vehicleRoot);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getDealerId()).isEqualTo(vehicleRoot.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());



        vehicle.setModifiedUser("micah");
        vehicle.setModifiedDtm(DateTime.now().toString());
        vehicle.setPrice(13500);
        vehicle.setPrice(24500);

        vehicle = vehicleService.upsertDealerShipVehicle(vehicle);
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getModifiedUser()).isEqualTo("micah");
        assertThat(vehicle.getDealerId()).isEqualTo(vehicleRoot.getDealerId());
        assertThat(vehicle.getStockNum()).isEqualTo(vehicleRoot.getStockNum());




    }

    @Test(dependsOnMethods = {"testUpsertDealerVehicle"})
    public void testGetByDealerId() throws Exception {

        VehicleModelCollection dvmc = vehicleService.get(vehicleRoot.getDealerId());
        assertThat(dvmc).isNotNull();
        assertThat(dvmc.getClientVehicles()).isNotNull();
        assertThat(dvmc.getClientVehicles().size()).isGreaterThan(0);
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
                .dealerId("ad44d405-8240-4035-98c9-2b9b528b2e86")
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