package com.saat.auto.cafe.api.controllers.v1;

import com.saat.auto.cafe.api.ApiConstants;
import com.saat.auto.cafe.api.WebHelper;
import com.saat.auto.cafe.api.storage.StorageService;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.services.VehicleService;
import com.saat.auto.cafe.common.models.VehicleModel;
import com.saat.auto.cafe.service.impl.VehicleServiceUtility;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.xml.ws.WebServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by micahcoletti on 8/2/16.
 */
@Api("Vehicle Endpoint(s)")
@RestController
@CrossOrigin
@RequestMapping(VehicleController.PATH)
public class VehicleController {

    public static final String PATH = WebHelper.API_V1_PATH + ApiConstants.VEHICLE_MP;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleServiceUtility vehicleServiceUtility;
    @Autowired
    private StorageService storageService;

    /**
     * Endpoint for Adding or Updating a Vehicle in the System
     */
    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of Stok Number and DealerId",
            response = String.class,
            responseContainer = "String")
    public ResponseEntity addNewVehicle(@RequestBody VehicleModel vehicleModel) {

        try {
            log.debug("Attempting to add new Vehicle into the System for StockNum {} and DealerShipId {}", vehicleModel.getStockNum(), vehicleModel.getDealerId());

            // Check to make sure the Vehicle does not exist
            VehicleModel vehicleCHeck = vehicleService.get(vehicleModel.getDealerId(), vehicleModel.getId(), true);
            if (vehicleCHeck != null) {
                log.error("The Vehicle with StockNum {} that is attempting to be Added already exists in the system", vehicleModel.getStockNum());
                return ResponseEntity.badRequest().body(String.format("The Vehicle with StockNum %s that is attempting to be Added already exists in the system", vehicleModel.getStockNum()));
            }
            // Set the dates
            vehicleModel.setCreatedDtm(DateTime.now().toString());
            vehicleModel.setModifiedDtm(DateTime.now().toString());
            vehicleService.upsertDealerShipVehicle(vehicleModel);
            log.debug("Vehicle with StockNum {} has been added in the system", vehicleModel.getStockNum());
            return ResponseEntity.ok(String.format("Vehicle for Stock Number: %s has been Added in the system", vehicleModel.getStockNum()));
        } catch (VehicleServiceException e) {
            log.error("Error Adding the Vehicle for StockNum {} for dealershipId {}", vehicleModel.getStockNum(), vehicleModel.getDealerId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint to update a Vehicle object in the system
     *
     * @param vehicleModel the vehicle to update
     * @return HttpStatus Response
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of Stok Number and DealerId",
            response = String.class,
            responseContainer = "String")
    public ResponseEntity updateVehicle(@RequestBody VehicleModel vehicleModel) {

        try {
            log.debug("Attempting to update the Vehicle in the System for StockNum {} and DealerShipId {}", vehicleModel.getStockNum(), vehicleModel.getDealerId());

            // Check to make sure the Vehicle exists in the system
            VehicleModel vehicleCHeck = vehicleService.get(vehicleModel.getDealerId(), vehicleModel.getId(), true);
            if (vehicleCHeck == null) {
                log.error("The Vehicle that is attempting to be updated does not exist in the system");
                return ResponseEntity.badRequest().body("The vehicle does not exist in the system");
            }

            // Update the Vehicle
            vehicleModel.setModifiedDtm(DateTime.now().toString());
            vehicleService.upsertDealerShipVehicle(vehicleModel);
            log.debug("Vehicle with StockNum {} has been updated in the system", vehicleModel.getStockNum());
            return ResponseEntity.ok(String.format("Vehicle for Stock Number: %s has been Updated in the system", vehicleModel.getStockNum()));
        } catch (VehicleServiceException e) {
            log.error("Error Updating the Vehicle for StockNum {} for dealershipId {}", vehicleModel.getStockNum(), vehicleModel.getDealerId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint that get a Vehicle Model based off Id and ClientId
     *
     * @param vehicleId    the Stock Number
     * @param dealerShipId the client Id
     * @return a JSON object representing the Vehicle Model Object
     */
    @RequestMapping(
            // value = "/vehicle",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of VehicleId and DealerId",
            response = VehicleModel.class,
            responseContainer = "VehicleModel")
    public ResponseEntity getVehicle(@RequestParam(value = "vehicleId") String vehicleId, @RequestParam(value = "dealershipId") String dealerShipId, @RequestParam(value = "resetCache", required = false) boolean resetCache) {

        try {

            if (vehicleId != null) {
                log.debug("Getting Vehicle Model StockNum {} and DealerShip Id {}", vehicleId, dealerShipId);
                VehicleModel model = vehicleService.get(dealerShipId, vehicleId, resetCache);

                if (model != null) {
                    return ResponseEntity.ok(model);
                }
            }
        } catch (VehicleServiceException e) {
            log.error("Error Getting the Vehicle for StockNum {} for dealershipId {}", vehicleId, dealerShipId);
            throw new WebServiceException(e);
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();

    }


    @RequestMapping(
            value = "/stockNum/{stockNum}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based off StockNum",
            response = VehicleModel.class,
            responseContainer = "VehicleModel")
    public ResponseEntity getVehicleByStock(@PathVariable(value = "stockNum") String stockNum, @RequestParam(value = "resetCache", required = false) boolean resetCache) {

        try {

            if (stockNum != null) {
                log.debug("Getting Vehicle Model by StockNum {}", stockNum);
                VehicleModel model = vehicleService.getByStockNum(stockNum, resetCache);

                if (model != null) {
                    return ResponseEntity.ok(model);
                }
            }
        } catch (VehicleServiceException e) {
            log.error("Error Getting the Vehicle for StockNum {} - {}", stockNum, e.getMessage());
            throw new WebServiceException(e);
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();

    }

    /**
     * Endpoint to get Vehicle Model by VIN
     *
     * @param vin        the VIN number to use
     * @param resetCache set to true if you want to reset the cache
     */
    @RequestMapping(
            value = "/vin/{vin}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of VIN Number",
            response = VehicleModel.class,
            responseContainer = "VehicleModel")
    public ResponseEntity getVehicleByVin(@PathVariable("vin") String vin, @RequestParam(value = "resetCache", required = false) boolean resetCache) {

        try {

            if (vin != null) {
                log.debug("Getting Vehicle Model By VIN {}", vin);
                VehicleModel model = vehicleService.getByVin(vin, resetCache);

                if (model != null) {
                    return ResponseEntity.ok(model);
                }
            }
            return ResponseEntity.badRequest().body("No VIN Was provided");
        } catch (VehicleServiceException e) {
            log.error("Error Getting the Vehicle for VIN {} - {}", vin, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /**
     * Endpoint that returns a Vehicle Collection based off DealerShip Id
     */

    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Get an Array of the DealerShip Vehicles",
            response = VehicleModel.class,
            responseContainer = "VehicleModel")
    public ResponseEntity getDealerShipVehicles(@RequestParam(value = "dealerId") String dealerId, @RequestParam(value = "resetCache", required = false) boolean resetCache) {
        log.debug("Getting the Vehicle Collection for DealerShip Id: {}", dealerId);
        try {
            if (dealerId != null) {
                List<VehicleModel> vehicles = vehicleService.get(dealerId, resetCache);

                if (vehicles != null) {
                    return ResponseEntity.ok(vehicles);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The DealerShip Id was not Provided");


        } catch (VehicleServiceException e) {
            log.error("Error Getting the Vehicles Collection for ClientId: {}", dealerId);
            throw new WebServiceException(e);
        }
    }

    @RequestMapping(
            value = "/file",
            method = RequestMethod.POST
    )
    @ApiOperation(value = "Get an Array of the DealerShip Vehicles",
            response = VehicleModel.class,
            responseContainer = "VehicleModel")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        log.debug("Uploading file {} to data store", file.getName());

        storageService.store(file);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint that reset the vehicle make totals for a given dealership
     *
     * @param dealerId the dealership to process
     */
    @RequestMapping(
            value = "/reset/vehicleMakeTotals",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ApiOperation(value = "Resets the Vehicle Make Totals for a given dealerShip",
            response = String.class,
            responseContainer = "String")
    public ResponseEntity resetVehicleMakeTotals(@RequestParam(value = "dealerId") String dealerId) {
        log.debug("Resetting the Vehicle Make Totals for DealerShip Id: {}", dealerId);
        try {
            if (dealerId != null) {

                vehicleServiceUtility.updateVehicleMakeTotals(dealerId);
                return ResponseEntity.ok("Reset Vehicle Make Totals");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The DealerShip Id was not Provided");


        } catch (VehicleServiceException e) {
            log.error("Error Resetting the Vehicle Make Totals for dealership: {}", dealerId);
            throw new WebServiceException(e);
        }
    }


}
