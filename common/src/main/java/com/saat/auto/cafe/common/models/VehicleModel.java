package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.Vehicle;

import org.apache.cassandra.utils.UUIDGen;
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
            value = "VIN"
    )
    private String vin;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "Options"
    )
    private String options;
    @ApiModelProperty(
            position = 6,
            required = true,
            value = "Vehicle Price"
    )
    private double price;
    @ApiModelProperty(
            position = 6,
            required = true,
            value = "Invoice Amount"
    )
    private double invoiceAmount;
    private String bodyStyle;
    private String extColor;
    private String intColor;
    private double mileage;
    private String make;
    private String model;
    private int year;
    private String trim;
    @ApiModelProperty(
            position = 8,
            required = true,
            value = "locations"
    )
    private String lotLocation;
    @ApiModelProperty(
            position = 9,
            required = true,
            value = "User who added the Vehicle to the System"
    )
    private String createdUser;
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
    private String modifiedUser;
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
        cv.setDealershipId(UUID.fromString(dealerId));
        cv.setStockNum(stockNum);
        cv.setOptions(options);
        cv.setVin(vin);
        cv.setInvoiceAmount(invoiceAmount);
        cv.setPrice(price);
        cv.setExtColor(extColor);
        cv.setIntColor(intColor);
        cv.setBodyStyle(bodyStyle);
        cv.setMileage(mileage);
        cv.setMake(make);
        cv.setModel(model);
        cv.setYear(year);
        cv.setTrim(trim);
        cv.setLotLocation(lotLocation);
        cv.setCreatedUser(createdUser);
        cv.setCreated(UUIDGen.getTimeUUID(DateTime.parse(createdDtm).getMillis()));
        cv.setModifiedUser(modifiedUser);
        cv.setModified(UUIDGen.getTimeUUID(DateTime.parse(modifiedDtm).getMillis()));
        return cv;


    }



}
