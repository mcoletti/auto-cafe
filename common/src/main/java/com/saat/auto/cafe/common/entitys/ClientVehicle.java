package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

//import org.springframework.data.cassandra.mapping.Column;
//import org.springframework.data.cassandra.mapping.PrimaryKey;

//import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by mcoletti on 6/17/16.
 */
@Table(
        name = "client_vehicles",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class ClientVehicle {

    @PartitionKey
    @Column(name = "client_id")
    private UUID clientId;
    @Column(name = "vehicle_id")
    private UUID vehicleId;
//    @Column(name = "created")
//    private UUID created;
    @Column(name = "stock_num")
    @Getter
    private int stockNum;
    @Column(name = "short_desc")
    private String shortDesc;
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


    public Insert getInsertStatement(Cluster cluster){


//        Insert insert = QueryBuilder.insertInto("client_vehicles")
//                .name("client_id",clientId).name("vehicle_id",vehicleId).name("stock_num",stockNum)
//                .name("short_desc",shortDesc).name("price",price)
//                .name("created_dtm", createdDtm.toDate()).name("created_by",createdBy)
//                .name("details", details.toUdtValue(cluster))
//                .name("location",location.toUdtValue(cluster))
//                .name("modified_by", modifiedBy)
//                .name("modified_dtm",modifiedDtm.toDate());


        return null;
    }

    public Statement getUpdateStatement(Cluster cluster){

        return null;

//        Statement update = QueryBuilder.update("client_vehicles")
//                .with(set("stock_num",stockNum)).and(set("price",price))
//                .and(set("short_desc",shortDesc)).and(set("details",details.toUdtValue(cluster)))
//                .and(set("modified_by",modifiedBy)).and(set("modified_dtm",modifiedDtm.toDate()))
//                .and(set("location",location.toUdtValue(cluster)))
//                .where(eq("client_id",clientId)).and(eq("vehicle_id",vehicleId));
//        return update;
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
