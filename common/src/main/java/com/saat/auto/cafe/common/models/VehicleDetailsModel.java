package com.saat.auto.cafe.common.models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.entitys.Location;
import com.saat.auto.cafe.common.entitys.VehicleDetails;

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

@Builder
@Data
public class VehicleDetailsModel {
    @Getter
    private String clientId;
    @Getter
    private String id;
    private String bodyStyle;
    private String category;
    private String extColor;
    private String intColor;
    private String keyName;
    private Location location;
    private String manufacture;
    private String mileage;
    private String make;
    private String model;
    private int year;
    private BigDecimal price;
    private int stockNum;
    private String trim;
    private String createdBy;
    private String createdDtm;
    private String modifiedBy;
    private String modifiedDtm;

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
     * Method to convert from a Entity to a Model
     * @return
     */
    public VehicleDetails toEntity(){

        VehicleDetails vdEntity = VehicleDetails.builder()
                .clientId(UUID.fromString(clientId))
                .id(UUID.fromString(id))
                .bodyStyle(bodyStyle).category(category)
                .extColor(extColor).intColor(intColor)
                .keyName(keyName).location(location)
                .manufacture(manufacture).make(make)
                .year(year).model(model)
                .price(price).stockNum(stockNum)
                .trim(trim)
                .createdBy(createdBy).createdDtm(DateTime.parse(createdDtm).toDate())
                .modifiedBy(modifiedBy).modifiedDtm(DateTime.parse(modifiedDtm).toDate()).build();
        return vdEntity;

    }

}
