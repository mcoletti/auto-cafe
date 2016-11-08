package com.saat.auto.cafe.api.controllers.v1;

import com.saat.auto.cafe.api.ApiConstants;
import com.saat.auto.cafe.api.WebHelper;
import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.exceptions.ClientServiceException;
import com.saat.auto.cafe.common.interfaces.services.ClientService;
import com.saat.auto.cafe.common.models.ClientModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by micahcoletti on 11/7/16.
 */
@Api("Client Endpoints")
@RestController
@CrossOrigin
@RequestMapping(ClientController.PATH)
public class ClientController {

    public static final String PATH = WebHelper.API_V1_PATH + ApiConstants.CLIENT_MP;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    /**
     * Endpoint to Add or Update a Client in the system
     * @param clientModel
     * @return
     */
    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of Stok Number and DealerId",
            response = String.class,
            responseContainer = "ClientModel")
    public ResponseEntity upsertClient(@RequestBody ClientModel clientModel) {

        try {

            clientService.upsertClient(clientModel);
            return ResponseEntity.ok("Success");
        } catch (ClientServiceException e) {
            log.error("Error Adding or Updating Client {} - {}", clientModel.getName(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    /**
     * Endpoint that gets a Client based off Id
     *
     * @param clientId the client id
     */
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of Stok Number and DealerId",
            response = ClientModel.class,
            responseContainer = "ClientModel")
    public ResponseEntity getClient(@RequestParam(value = "clientId") String clientId,@RequestParam(value = "resetCache", required = false) boolean resetCache) {

        try {

            if (clientId != null) {
                log.debug("Getting Client for ClientId {}", clientId);
                ClientModel model = clientService.getClient(clientId,resetCache);

                if (model != null) {
                    return ResponseEntity.ok(model);
                }
            }
            return ResponseEntity.badRequest().build();
        } catch (ClientServiceException e) {
            log.error("Error Getting Client for Id {} - {}", clientId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint for getting a list of all clients in the system
     * @return
     */
    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ApiOperation(value = "Get a Vehicle Model Object based of Stok Number and DealerId",
            response = ClientModel[].class,
            responseContainer = "ClientModel")
    public ResponseEntity getClients(@RequestParam(value = "resetCache", required = false) boolean resetCache) {


        try {
            List<ClientModel> clientList = clientService.getClientList(resetCache);

            if ((clientList != null) && (clientList.size() != 0)) {
                return ResponseEntity.ok(clientList);
            }

            return ResponseEntity.noContent().build();

        } catch (ClientServiceException e) {
            log.error("Error Getting Client List - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
