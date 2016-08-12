package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.ClientVehicle;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 8/3/16.
 */
@Builder
@Data
@ApiModel(description = "The Client Vehicle Data Model")
public class ClientVehiclesModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "The Client Id"
    )
    private String clientId;
    @ApiModelProperty(
            position = 2,
            required = true,
            value = "The Vehicle Id"
    )
    private String vehicleId;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The stock number"
    )
    private int stockNum;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "Short Description of the Vehicle"
    )
    private String shortDesc;
    @ApiModelProperty(
            position = 5,
            required = true,
            value = "Vehicle Price"
    )
    private double price;
    @ApiModelProperty(
            position = 6,
            required = true,
            value = "Vehicle Detail"
    )
    private VehicleDetailModel details;
    @ApiModelProperty(
            position = 7,
            required = true,
            value = "location"
    )
    private LocationModel location;
    @ApiModelProperty(
            position = 8,
            required = true,
            value = "User who added the Vehicle to the System"
    )
    private String createdBy;
    @ApiModelProperty(
            position = 9,
            required = true,
            value = "The created DateTime"
    )
    private String createdDtm;
    @ApiModelProperty(
            position = 10,
            required = true,
            value ="User who modified the Vehicle to the System"
    )
    private String modifiedBy;
    @ApiModelProperty(
            position = 11,
            required = true,
            value = "The modified DateTime"
    )
    private String modifiedDtm;

    /**
     * Method to covert the model object to the entity object
     * @return
     */
    public ClientVehicle toEntity(){


        return ClientVehicle.builder()
                .clientId(UUID.fromString(clientId))
                .vehicleId(UUID.fromString(vehicleId))
                .stockNum(stockNum)
                .shortDesc(shortDesc)
                .price(price)
                .details(details.toEntity())
                .location(location.toEntity())
                .createdBy(createdBy)
                .createdDtm(DateTime.parse(createdDtm))
                .modifiedBy(modifiedBy)
                .modifiedDtm(DateTime.parse(modifiedDtm)).build();


    }



}
