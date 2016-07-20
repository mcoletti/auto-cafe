package com.saat.auto.cafe.common.models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.wordnik.swagger.annotations.ApiModel;

import org.joda.time.DateTime;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import com.datastax.driver.mapping.annotations.Table;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by mcoletti on 5/10/16.
 */
@Table(
        name = "vehicle_details",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)

@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "THe Vehicle inventory Entity Model")
@Builder
public class VehicleDetails {

    @PrimaryKey
    @PartitionKey(0)
    @Getter
    private UUID clientId;
    @PrimaryKey
    @PartitionKey(1)
    @Getter
    private UUID id;
    @Column
    private String bodyStyle;
    @Column
    private String category;
    @Column
    private String extColor;
    @Column
    private String intColor;
    @Column
    @Getter
    private String keyName;
    @Column
    private Location location;
    @Column
    private String manufacture;
    @Column
    private String mileage;
    @Column
    private String make;
    @Column
    private String model;
    @Column(value = "vehicle_year")
    private int year;
    @Column
    private BigDecimal price;
    @Column
    private int stockNum;
    @Column
    private String trim;
    @Column
    private String createdBy;
    @Column
    private DateTime createdDtm;
    @Column
    private String modifiedBy;
    @Column
    private DateTime modifiedDtm;

    /**
     * Method that will create a Cassandrs DB Insert Statement based of the VehicleDetails object
     * data
     *
     * @param cluster the cassandra cluster
     * @return a new cassandra Insert Statement
     */
    public Insert getInsertStatement(Cluster cluster) {


        Insert insert = QueryBuilder.insertInto("autocafe", "vehicle_details")
                .value("id", id)
                .value("client_id", clientId)
                .value("body_style", bodyStyle)
                .value("category", category)
                .value("ext_color", extColor)
                .value("int_color", intColor)
                .value("key_name", keyName)
                .value("location", location.toUdtValue(cluster))
                .value("manufacture", manufacture)
                .value("make", make)
                .value("model", model)
                .value("vehicle_year", year)
                .value("price", price)
                .value("stock_num", stockNum)
                .value("trim", trim)
                .value("created_by", createdBy)
                .value("created_dtm", createdDtm.toDate())
                .value("modified_by", modifiedBy)
                .value("modified_dtm", modifiedDtm.toDate());
        return insert;
    }

}
