package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.VehicleModel;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

//import org.springframework.data.cassandra.mapping.Column;
//import org.springframework.data.cassandra.mapping.PrimaryKey;

//import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by mcoletti on 6/17/16.
 */
@Table(
        name = "dealer_vehicles",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class Vehicle {

    @PartitionKey
    @Column(name = "dealer_id")
    private UUID dealerId;
//    @Column(name = "created")
//    private UUID created;
    @Column(name = "stock_num")
    private String stockNum;
    @Column(name = "short_desc")
    private String shortDesc;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "details")
    private VehicleDetail details;
    private Location location;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_dtm")
    private Date createdDtm;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_dtm")
    private Date modifiedDtm;

    /**
     * Method to covent the Entity object to the Model Object
     * @return
     */
    public VehicleModel toModel(){
        return VehicleModel.builder()
                .dealerId(dealerId.toString())
                .stockNum(stockNum)
                .shortDesc(shortDesc)
                .description(description)
                .price(price)
                .details(details.toModel())
                .location(location.toModel())
                .createdBy(createdBy)
                .createdDtm(new DateTime(createdDtm.getTime()).toString())
                .modifiedBy(modifiedBy)
                .modifiedDtm(new DateTime(modifiedDtm.getTime()).toString()).build();

//        return VehicleModel.builder()
//                .dealerId(dealerId.toString()).vehicleId(vehicleId.toString())
//                .keyName(keyName).createdDtm(new DateTime(createdDtm.getTime()).toString()).build();
    }

}
