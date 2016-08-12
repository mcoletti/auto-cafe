package com.saat.auto.cafe.api.endpoints.v1;

import com.saat.auto.cafe.api.ApiConstants;
import com.saat.auto.cafe.api.WebHelper;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.ClientVehicleCollectionModel;
import com.saat.auto.cafe.common.models.VehicleDetailModel;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by micahcoletti on 8/2/16.
 */
@Api("Vehicle Endpoint(s)")
@RestController
@CrossOrigin
@RequestMapping(VehicleEndPoint.PATH)
public class VehicleEndPoint {

    public static final String PATH = WebHelper.API_V1_PATH + ApiConstants.VEHICLE_MP;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private VehicleService vehicleService;

    @Autowired
    public VehicleEndPoint(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * Endpoint that get a Vehicle Model based off Id and ClientId
     *
     * @param vehicleId the vehicleId
     * @param clientId  the client Id
     * @return a JSON object representing the Vehicle Model Object
     */

    @RequestMapping(
            value = "/vehicle",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of VehicleId and ClientId",
            response = VehicleDetailModel.class,
            responseContainer = "List")
    public ResponseEntity getVehicle(@RequestParam(value = "vehicleId") String vehicleId, @RequestParam(value = "clientId") String clientId) {

//        try {
//
//            if (vehicleId != null) {
//                log.debug("Getting Vehicle Model for ID: {} and Client Id: {}", vehicleId, clientId);
//                VehicleDetailModel model = vehicleService.getVehicleDetailsModel(vehicleId, clientId);
//
//                if (model != null) {
//                    return ResponseEntity.ok(model);
//                }
//            }
//        } catch (VehicleServiceException e) {
//            log.error("Error Getting the Vehicle for VehicleId: {} and ClientId: {}", vehicleId, clientId);
//            throw new WebServiceException(e);
//        }

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();

    }

    /**
     * Endpoint that returns a Vehicle Collection based off Client Id
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(
            value = "/client/{clientId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiOperation(value = "Get an Array of the Client Vehicles",
            response = ClientVehicleCollectionModel.class,
            responseContainer = "ClientVehicleCollectionModel")
    public ResponseEntity getClientVehicles(@PathVariable(value = "clientId") String clientId) {
        log.debug("Getting the ClientVehicle Collection for Client Id: {}", clientId);
//        try {
//            if (clientId != null) {
//                ClientVehicleCollectionModel clientVehiclesCollection = vehicleService.getClientVehicles(clientId);
//
//                if (clientVehiclesCollection != null) {
//                    return ResponseEntity.ok(clientVehiclesCollection);
//                }
//                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//            }
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The Client Id was not Provided");
//
//
//        } catch (VehicleServiceException e) {
//            log.error("Error Getting the Vehicles Collection for ClientId: {}", clientId);
//            throw new WebServiceException(e);
//        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }


}
