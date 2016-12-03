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
            position = 1,
            required = true,
            value = "The Vehicle Unique Id"
    )
    private String id;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The stock number"
    )
    private String stockNum;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The Short Description of the vehicle"
    )
    private String shortDescription;
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
    private String priceFormatted;
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
            value = "Lot Id"
    )
    private long lotId;
    @ApiModelProperty(
            position = 8,
            required = true,
            value = "Lot Location"
    )
    private String lotLocation;
    @ApiModelProperty(
            position = 8,
            required = true,
            value = "The Header Image Url"
    )
    private String imgUrl;
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

        Vehicle vehicle = new Vehicle();
        vehicle.setDealershipId(UUID.fromString(dealerId));
        vehicle.setId(UUID.fromString(id));
        vehicle.setStockNum(stockNum);
        vehicle.setShortDescription(shortDescription);
        vehicle.setOptions(options);
        vehicle.setVin(vin);
        vehicle.setInvoiceAmount(invoiceAmount);
        vehicle.setPrice(price);
        vehicle.setExtColor(extColor);
        vehicle.setIntColor(intColor);
        vehicle.setBodyStyle(bodyStyle);
        vehicle.setMileage(mileage);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setTrim(trim);
        vehicle.setLotId(lotId);
        vehicle.setLotLocation(lotLocation);
        vehicle.setImgUrl(imgUrl);
        vehicle.setCreatedUser(createdUser);
        vehicle.setCreated(UUIDGen.getTimeUUID(DateTime.parse(createdDtm).getMillis()));
        vehicle.setModifiedUser(modifiedUser);
        vehicle.setModified(UUIDGen.getTimeUUID(DateTime.parse(modifiedDtm).getMillis()));
        return vehicle;


    }



}
