package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;

import org.joda.time.DateTime;
//import org.springframework.cassandra.core.PrimaryKeyType;
//import org.springframework.data.cassandra.mapping.Column;
//import org.springframework.data.cassandra.mapping.Indexed;
//import org.springframework.data.cassandra.mapping.PrimaryKey;
//import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
//
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;
import static com.datastax.driver.core.querybuilder.QueryBuilder.set;

/**
 * Created by mcoletti on 5/17/16.
 */
@Table(
        name = "clients",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class Client {

    @PartitionKey
    private UUID id;
    @Column(name = "client_name")
    private String clientName;
    @Frozen
    @Column(name = "locations")
    private Map<String,Location> locations;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_dtm")
    private Date createdDtm;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Column(name = "modified_dtm")
    private Date modifiedDtm;



    public Insert getInsertStatement(Cluster cluster) {
//        Insert insert = QueryBuilder.insertInto("clients")
//                .value("id",id).value("client_name",clientName)
//                .value("created_by",createdBy).value("created_dtm",createdDtm.toDate())
//                .value("modified_by",modifiedBy).value("modified_dtm",modifiedDtm.toDate())
//                .value("location",location.toUdtValue(cluster));

        return null;
    }

    public Statement getUpdateStatement(Cluster cluster){

//        Statement update = QueryBuilder.update("clients")
//                .with(set("location",location.toUdtValue(cluster))).and(set("modified_by",modifiedBy)).and(set("modified_dtm",modifiedDtm.toDate()))
//                .where(eq("id",id)).and(eq("client_name",clientName));

        return null;
    }

}
