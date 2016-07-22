package com.saat.auto.cafe.api.controllers.v1;

import com.saat.auto.cafe.api.WebHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by mcoletti on 6/1/16.
 */
@Api("VehicleController")
@RestController
@RequestMapping(VehicleController.PATH)
public class VehicleController {

    public static final String PATH = WebHelper.API_V1_PATH + "/vehicles";

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Gets a list of Strings", response = List.class, responseContainer = "List")
    public Callable<ResponseEntity> getTestList() {
        // Returning a Callable causes Spring to run this method asynchronously on a different, background thread. This
        // allows the servlet framework to take other calls while this one is processing in the background.
        return () -> {
            List<String> list = new ArrayList<>();
            list.add("This is a test");
            return ResponseEntity.ok(list);
        };
    }

}
