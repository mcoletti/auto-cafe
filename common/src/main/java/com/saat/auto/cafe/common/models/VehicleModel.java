package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.Vehicle;

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
@ApiModel(description = "The DealerShip Vehicle Data Model")
public class VehicleModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "The DealerShip Id"
    )
    private String dealerId;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The stock number"
    )
    private String stockNum;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "Short Description of the Vehicle"
    )
    private String shortDesc;
    @ApiModelProperty(
            position = 5,
            required = true,
            value = "Detail Description of the Vehicle"
    )
    private String description;
    @ApiModelProperty(
            position = 6,
            required = true,
            value = "Vehicle Price"
    )
    private double price;
    @ApiModelProperty(
            position = 7,
            required = true,
            value = "Vehicle Detail"
    )
    private VehicleDetailModel details;
    @ApiModelProperty(
            position = 8,
            required = true,
            value = "location"
    )
    private LocationModel location;
    @ApiModelProperty(
            position = 9,
            required = true,
            value = "User who added the Vehicle to the System"
    )
    private String createdBy;
    @ApiModelProperty(
            position = 10,
            required = true,
            value = "The created DateTime"
    )
    private String createdDtm;
    @ApiModelProperty(
            position = 11,
            required = true,
            value ="User who modified the Vehicle to the System"
    )
    private String modifiedBy;
    @ApiModelProperty(
            position = 12,
            required = true,
            value = "The modified DateTime"
    )
    private String modifiedDtm;

    /**
     * Method to covert the model object to the entity object
     * @return
     */
    public Vehicle toEntity(){

        Vehicle cv = new Vehicle();
        cv.setDealerId(UUID.fromString(dealerId));
        cv.setStockNum(stockNum);
        cv.setShortDesc(shortDesc);
        cv.setDescription(description);
        cv.setPrice(price);
        cv.setDetails(details.toEntity());
        cv.setLocation(location.toEntity());
        cv.setCreatedBy(createdBy);
        cv.setCreatedDtm(DateTime.parse(createdDtm).toDate());
        cv.setModifiedBy(modifiedBy);
        cv.setModifiedDtm(DateTime.parse(modifiedDtm).toDate());
        return cv;


    }



}
