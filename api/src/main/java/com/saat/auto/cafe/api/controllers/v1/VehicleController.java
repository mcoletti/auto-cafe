package com.saat.auto.cafe.api.controllers.v1;

import com.saat.auto.cafe.api.WebHelper;
import com.wordnik.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mcoletti on 6/1/16.
 */
@Api("Examples")
@RestController
@RequestMapping(VehicleController.PATH)
public class VehicleController {

    public static final String PATH = WebHelper.API_V1_PATH + "/vehicles";

    private final Logger log = LoggerFactory.getLogger(this.getClass());
}
