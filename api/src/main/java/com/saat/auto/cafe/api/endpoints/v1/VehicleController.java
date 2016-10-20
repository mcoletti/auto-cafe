package com.saat.auto.cafe.api.endpoints.v1;

import com.saat.auto.cafe.api.ApiConstants;
import com.saat.auto.cafe.api.WebHelper;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.services.VehicleService;
import com.saat.auto.cafe.common.models.VehicleModelCollection;
import com.saat.auto.cafe.common.models.VehicleModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Endpoint that get a Vehicle Model based off Id and ClientId
     *
     * @param stockNum the Stock Number
     * @param clientId  the client Id
     * @return a JSON object representing the Vehicle Model Object
     */

    @RequestMapping(
            value = "/vehicle",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of Stok Number and DealerId",
            response = VehicleModel.class,
            responseContainer = "VehicleModel")
    public ResponseEntity getVehicle(@RequestParam(value = "stockNum") String stockNum, @RequestParam(value = "dealershipId") String clientId) {

        try {

            if (stockNum != null) {
                log.debug("Getting Vehicle Model for ID: {} and DealerShip Id: {}", stockNum, clientId);
                VehicleModel model = vehicleService.get(clientId,stockNum);

                if (model != null) {
                    return ResponseEntity.ok(model);
                }
            }
        } catch (VehicleServiceException e) {
            log.error("Error Getting the Vehicle for VehicleId: {} and ClientId: {}", stockNum, clientId);
            throw new WebServiceException(e);
        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();

    }

    /**
     * Endpoint that returns a Vehicle Collection based off DealerShip Id
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(
            value = "/dealer/{dealerId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Get an Array of the DealerShip Vehicles",
            response = VehicleModelCollection.class,
            responseContainer = "VehicleModelCollection")
    public ResponseEntity getDealerVehicles(@PathVariable(value = "dealerId") String dealerId) {
        log.debug("Getting the Vehicle Collection for DealerShip Id: {}", dealerId);
        try {
            if (dealerId != null) {
                VehicleModelCollection clientVehiclesCollection = vehicleService.get(dealerId);

                if (clientVehiclesCollection != null) {
                    return ResponseEntity.ok(clientVehiclesCollection);
                }
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The DealerShip Id was not Provided");


        } catch (VehicleServiceException e) {
            log.error("Error Getting the Vehicles Collection for ClientId: {}", dealerId);
            throw new WebServiceException(e);
        }
    }


}
