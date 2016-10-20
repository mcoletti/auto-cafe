package com.saat.auto.cafe.api.controllers.v1;

import com.saat.auto.cafe.api.ApiConstants;
import com.saat.auto.cafe.api.WebHelper;
import com.saat.auto.cafe.common.exceptions.DealershipServiceException;
import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.DealerShipModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import javax.xml.ws.WebServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by micahcoletti on 10/20/16.
 */
@Api("DealerShip Endpoint(s)")
@RestController
@CrossOrigin
@RequestMapping(DealerShipController.PATH)
public class DealerShipController {

    public static final String PATH = WebHelper.API_V1_PATH + ApiConstants.DEALERSHIP_MP;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private final DealerShipService dealerShipService;

    @Autowired
    public DealerShipController(DealerShipService dealerShipService) {
        this.dealerShipService = dealerShipService;
    }

    /**
     * Get a dealership endpoint
     *
     * @param dealerShipId the dealer Id
     * @return a dealership JSON object
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a dealership by Id",
            response = DealerShipModel.class,
            responseContainer = "DealerShipModel")
    public ResponseEntity getDealerShip(@RequestParam(value = "dealerShipId") String dealerShipId) {


        log.debug("Getting dealerShip for Id {}", dealerShipId);
        if (dealerShipId == null) {
            return ResponseEntity.badRequest().build();
        }


        try {
            DealerShipModel dealerShip = dealerShipService.get(dealerShipId);

            return ResponseEntity.ok(dealerShip);

        } catch (DealershipServiceException e) {
            log.error("Error getting dealership for Id {} - {}", dealerShipId, e.getMessage());
            throw new WebServiceException(e);
        }
    }

    /**
     * Add or Update a dealership endpoint
     *
     * @param dealerShip the dealership to add or update
     * @return the newly added or updated dealership
     */
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Add or Update a dealerShip",
            response = DealerShipModel.class,
            responseContainer = "DealerShipModel")
    public ResponseEntity upsert(@RequestBody DealerShipModel dealerShip) {


        log.debug("Getting dealerShip {}", dealerShip.getName());
        if (dealerShip == null) {
            return ResponseEntity.badRequest().build();
        }


        try {
            // Set the Id if not already set
            if ((dealerShip.getId() == null) || (dealerShip.getId().length() == 0)) {
                dealerShip.setId(UUID.randomUUID().toString());
            }


            dealerShipService.upsertDealserShip(dealerShip);

            return ResponseEntity.ok(dealerShip);

        } catch (DealershipServiceException e) {
            log.error("Error adding or updating dealership {} - {}", dealerShip.getName(), e.getMessage());
            throw new WebServiceException(e);
        }
    }

    /**
     * Get a list of dealership endpoint by client Id
     *
     * @param clientId the clientId to filter for
     * @return a JSON array of dealerships
     */
    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a list of dealerships by clientId",
            response = DealerShipModel.class,
            responseContainer = "DealerShipModel")
    public ResponseEntity list(@RequestParam(value = "clientId") String clientId) {


        log.debug("Getting list of dealerShips for client Id {}", clientId);
        if (clientId == null) {
            return ResponseEntity.badRequest().build();
        }


        try {

            List<DealerShipModel> dealerShips = dealerShipService.getDealerShips(clientId);
            return ResponseEntity.ok(dealerShips);

        } catch (DealershipServiceException e) {
            log.error("Error getting list of dealerships for clientId {} - {}", clientId, e.getMessage());
            throw new WebServiceException(e);
        }
    }

}
