package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;

import org.joda.time.DateTime;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.Indexed;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.util.UUID;

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
@Data
public class Clients {

    @PrimaryKey
    private UUID id;
    @Indexed
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

    public Statement getUpdateStatement(){

        Statement update = QueryBuilder.update("clients")
                .with(set("client_name",clientName)).and(set("modified_by",modifiedBy)).and(set("modified_dtm",modifiedDtm.toDate()))
                .where(eq("id",id));

        return update;
    }

}
