package com.saat.auto.cafe.common.models;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mcoletti on 5/17/16.
 */
@EqualsAndHashCode(callSuper=false)
@ApiModel(description = "The Vehicle Image Catalog Data Entity")
@Builder
public class CatalogImage {
    @ApiModelProperty(position = 1, required = true, value = "The Primary Key Id")
    @Getter
    @Setter
    private int id;
    @ApiModelProperty(position = 2, required = true, value = "Catalog CDN Url")
    @Getter
    @Setter
    private String cdnUrl;
    @ApiModelProperty(position = 3, required = true, value = "Image Type Id")
    @Getter
    @Setter
    private int imageTypeId;
    @ApiModelProperty(position = 4, required = true, value = "Vehicle Inventory Id")
    @Getter
    @Setter
    private int vehicleInventoryId;
    @ApiModelProperty(position = 5, required = true, value = "Image Type Object")
    @Getter
    @Setter
    private ImageType imageType;


    /**
     * Method to get the list of params
     */
    public List<Object> getParams() {


        List<Object> params = new ArrayList<>();
        // params.add(createdBy);
        // params.add(new Timestamp(createdDtm.getMillis()));
        // params.add(modifiedBy);
        // params.add(new Timestamp(modifiedDtm.getMillis()));
        // params.add(keyName);
        // params.add(stockNum);
        // params.add(extColor);
        // params.add(intColor);
        // params.add(trim);
        // params.add(vehiclePrice);
        // params.add(vehicleMileage);
        // params.add(vehicleCategoryId);
        // params.add(manufactureId);
        // params.add(vehicleModelId);
        // params.add(vehicleYearId);
        // params.add(bodyStyleId);
        // params.add(clientId);
        // params.add(locationId);


        return params;
    }
}
