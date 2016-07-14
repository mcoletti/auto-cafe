package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.db.SchemaConstants;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mcoletti on 5/10/16.
 */
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "THe Vehicle inventory Entity Model")
@Builder
public class VehicleDetails {
    @ApiModelProperty(position = 1, required = true, value = "The Primary Key Id")
    @Getter
    @Setter
    private int id;
    @ApiModelProperty(position = 2, required = true, value = "Created by User")
    @Getter
    @Setter
    private String createdBy;
    @ApiModelProperty(position = 3, required = true, value = "Created by DataTime")
    @Getter
    @Setter
    private DateTime createdDtm;
    @ApiModelProperty(position = 4, required = true, value = "Modified by User")
    @Getter
    @Setter
    private String modifiedBy;
    @ApiModelProperty(position = 5, required = true, value = "Modified by DataTime")
    @Getter
    @Setter
    private DateTime modifiedDtm;
    @ApiModelProperty(position = 6, required = true, value = "Vehicle KeyName")
    @Getter
    @Setter
    private String keyName;
    @ApiModelProperty(position = 8, required = true, value = "Vehicle Stock Number")
    @Getter
    @Setter
    private String stockNum;
    @ApiModelProperty(position = 9, required = true, value = "Vehicle Ext Color")
    @Getter
    @Setter
    private String extColor;
    @ApiModelProperty(position = 10, required = true, value = "Vehicle Interior Color")
    @Getter
    @Setter
    private String intColor;
    @ApiModelProperty(position = 11, required = true, value = "Vehicle Trim")
    @Getter
    @Setter
    private String trim;
    @ApiModelProperty(position = 12, required = true, value = "Vehicle Price")
    @Getter
    @Setter
    private BigDecimal vehiclePrice;
    @ApiModelProperty(position = 13, required = true, value = "Vehicle Mileage")
    @Getter
    @Setter
    private String vehicleMileage;
    @ApiModelProperty(position = 14, required = true, value = "Vehicle Category Id")
    @Getter
    @Setter
    private int vehicleCategoryId;
    @ApiModelProperty(position = 15, required = true, value = "Manufacture Id")
    @Getter
    @Setter
    private int manufactureId;
    @ApiModelProperty(position = 16, required = true, value = "Vehicle Model Id")
    @Getter
    @Setter
    private int vehicleModelId;
    @ApiModelProperty(position = 17, required = true, value = "Vehicle Year Id")
    @Getter
    @Setter
    private int vehicleYearId;
    @ApiModelProperty(position = 18, required = true, value = "Vehicle Body Style Id")
    @Getter
    @Setter
    private int bodyStyleId;
    @ApiModelProperty(position = 19, required = true, value = "The Clients Id this Inventory is Assigned to")
    @Getter
    @Setter
    private int clientId;
    @ApiModelProperty(position = 20, required = true, value = "The location the Inventory is located At")
    @Getter
    @Setter
    private int locationId;


    /**
     * Method to get the list of params
     */
    public List<Object> getParams() {


        List<Object> params = new ArrayList<>();
        params.add(createdBy);
        params.add(new Timestamp(createdDtm.getMillis()));
        params.add(modifiedBy);
        params.add(new Timestamp(modifiedDtm.getMillis()));
        params.add(keyName);
        params.add(stockNum);
        params.add(extColor);
        params.add(intColor);
        params.add(trim);
        params.add(vehiclePrice);
        params.add(vehicleMileage);
        params.add(vehicleCategoryId);
        params.add(manufactureId);
        params.add(vehicleModelId);
        params.add(vehicleYearId);
        params.add(bodyStyleId);
        params.add(clientId);
        params.add(locationId);


        return params;
    }

    /**
     * Method to get a result map of column name to values map
     */
    public Map<String, Object> getNameValueMap() {

        Map<String, Object> nvMap = new HashMap<>();

        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.KeyName, keyName);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.StockNum, stockNum);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.ExtColor, extColor);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.IntColor, intColor);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.Trim, trim);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.VehiclePrice, vehiclePrice);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.Category, vehicleCategoryId);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.ManufactureId, manufactureId);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.VehicleModelId, vehicleModelId);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.VehicleYearId, vehicleYearId);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.BodyStyleId, bodyStyleId);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.ClientId, clientId);
        nvMap.put(SchemaConstants.VehicleDetailsTable.Columns.Location, locationId);
        nvMap.put(SchemaConstants.Common.Columns.CreatedBy, createdBy);
        nvMap.put(SchemaConstants.Common.Columns.CreatedDtm, new Timestamp(createdDtm.getMillis()));
        nvMap.put(SchemaConstants.Common.Columns.ModifiedBy, modifiedBy);
        nvMap.put(SchemaConstants.Common.Columns.ModifiedDtm, new Timestamp(modifiedDtm.getMillis()));
        return nvMap;
    }

}
