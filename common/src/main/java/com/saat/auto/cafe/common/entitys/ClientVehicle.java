package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;

import org.apache.cassandra.db.marshal.TimeUUIDType;
import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

//import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by mcoletti on 6/17/16.
 */
@Table(
        name = "client_vehicles",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@Builder
@Data
public class ClientVehicle {

    @PrimaryKey(value = "client_id")
    private UUID clientId;
    @ClusteringColumn
    private UUID vehicleId;
//    @Column(value = "created")
//    private UUID created;
    @Column(value = "stock_num")
    @Getter
    private int stockNum;
    @Column(value = "short_desc")
    private String shortDesc;
    @Column(value = "price")
    private double price;
    @Column(value = "vehicle_detail")
    private VehicleDetail details;
    private Location location;
    @Column(value = "created_by")
    private String createdBy;
    @Column(value = "created_dtm")
    private DateTime createdDtm;
    @Column(value = "modifiedBy")
    private String modifiedBy;
    @Column(value = "modified_dtm")
    private DateTime modifiedDtm;


    public Insert getInsertStatement(Cluster cluster){


        Insert insert = QueryBuilder.insertInto("client_vehicles")
                .value("client_id",clientId).value("vehicle_id",vehicleId).value("stock_num",stockNum)
                .value("short_desc",shortDesc).value("price",price)
                .value("created_dtm", createdDtm.toDate()).value("created_by",createdBy)
                .value("details", details.toUdtValue(cluster))
                .value("location",location.toUdtValue(cluster))
                .value("modified_by", modifiedBy)
                .value("modified_dtm",modifiedDtm.toDate());


        return insert;
    }

    public Statement getUpdateStatement(Cluster cluster){

        Statement update = QueryBuilder.update("client_vehicles")
                .with(set("stock_num",stockNum)).and(set("price",price))
                .and(set("short_desc",shortDesc)).and(set("details",details.toUdtValue(cluster)))
                .and(set("modified_by",modifiedBy)).and(set("modified_dtm",modifiedDtm.toDate()))
                .and(set("location",location.toUdtValue(cluster)))
                .where(eq("client_id",clientId)).and(eq("vehicle_id",vehicleId));
        return update;
    }

    /**
     * Method to covent the Entity object to the Model Object
     * @return
     */
    public ClientVehiclesModel toModel(){
        return ClientVehiclesModel.builder()
                .clientId(clientId.toString())
                .vehicleId(vehicleId.toString())
                .stockNum(stockNum)
                .shortDesc(shortDesc)
                .price(price)
                .details(details.toModel())
                .location(location.toModel())
                .createdBy(createdBy)
                .createdDtm(createdDtm.toString())
                .modifiedBy(modifiedBy)
                .modifiedDtm(modifiedDtm.toString()).build();

//        return ClientVehiclesModel.builder()
//                .clientId(clientId.toString()).vehicleId(vehicleId.toString())
//                .keyName(keyName).createdDtm(new DateTime(createdDtm.getTime()).toString()).build();
    }

}
