package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;

import org.joda.time.DateTime;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.Indexed;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

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
@Builder
@Data
public class Client {

    @PrimaryKey
    private UUID id;
    @Column(value = "client_name")
    private String clientName;
    @Column
    private Location location;
    @Column(value = "created_by")
    private String createdBy;
    @Column(value = "created_dtm")
    private DateTime createdDtm;
    @Column(value = "modified_by")
    private String modifiedBy;
    @Column(value = "modified_dtm")
    private DateTime modifiedDtm;


    public Insert getInsertStatement(Cluster cluster) {
        Insert insert = QueryBuilder.insertInto("clients")
                .value("id",id).value("client_name",clientName)
                .value("created_by",createdBy).value("created_dtm",createdDtm.toDate())
                .value("modified_by",modifiedBy).value("modified_dtm",modifiedDtm.toDate())
                .value("location",location.toUdtValue(cluster));

        return insert;
    }

    public Statement getUpdateStatement(Cluster cluster){

        Statement update = QueryBuilder.update("clients")
                .with(set("location",location.toUdtValue(cluster))).and(set("modified_by",modifiedBy)).and(set("modified_dtm",modifiedDtm.toDate()))
                .where(eq("id",id)).and(eq("client_name",clientName));

        return update;
    }

}
