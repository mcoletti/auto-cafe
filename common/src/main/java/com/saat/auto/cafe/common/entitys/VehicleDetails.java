package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;

import org.joda.time.DateTime;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
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
@Builder
@Data
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
    private Date createdDtm;
    @Column
    private String modifiedBy;
    @Column
    private Date modifiedDtm;

    /**
     * Method that will create a Cassandrs DB Insert Statement based of the VehicleDetailsModel object
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
                .value("created_dtm", createdDtm)
                .value("modified_by", modifiedBy)
                .value("modified_dtm", modifiedDtm);
        return insert;
    }

    /**
     * Method to convert Entity to a Model
     * @return
     */
    public VehicleDetailsModel toModel(){

        VehicleDetailsModel vdModel = VehicleDetailsModel.builder()
                .clientId(clientId.toString())
                .id(id.toString())
                .bodyStyle(bodyStyle).category(category)
                .extColor(extColor).intColor(intColor)
                .keyName(keyName).location(location)
                .manufacture(manufacture).make(make)
                .year(year).model(model)
                .price(price).stockNum(stockNum)
                .trim(trim)
                .createdBy(createdBy).createdDtm(new DateTime(createdDtm).toString())
                .modifiedBy(modifiedBy).modifiedDtm(new DateTime(modifiedDtm).toString()).build();
        return vdModel;

    }

}
