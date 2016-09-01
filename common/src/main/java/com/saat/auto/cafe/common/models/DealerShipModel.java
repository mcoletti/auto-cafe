package com.saat.auto.cafe.common.models;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.saat.auto.cafe.common.entitys.Location;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 7/22/16.
 */
@Builder
@ApiModel(
        description = "THe DealerShip Model"
)
@Data
public class DealerShipModel {

    @ApiModelProperty(
            position = 1,
            required = true,
            value = "The DealerShip Id"
    )
    private String id;
    @ApiModelProperty(
            position = 2,
            required = true,
            value = "The DealerShip Name"
    )
    private String name;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The DealerShip Locations"
    )
    private Map<String,Location> locations;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "Who Created the Record in the DB"
    )
    private String createdBy;
    @ApiModelProperty(
            position = 5,
            required = true,
            value = "The Create DateTime"
    )
    private String createdDtm;
    @ApiModelProperty(
            position = 6,
            required = true,
            value = "Who Modified the Record in the DB"
    )
    private String modifiedBy;
    @ApiModelProperty(
            position = 7,
            required = true,
            value = "Modified DateTime"
    )
    private String modifiedDtm;

}
