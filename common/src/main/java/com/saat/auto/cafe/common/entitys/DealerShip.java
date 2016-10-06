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
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        name = "dealerships",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class DealerShip {


    @PartitionKey
    @Column(name = "client_id")
    private UUID clientId;
    @Column
    private UUID id;
    @Column
    private String name;
    @Column
    private List<Contact> contacts;
    @Column(name = "created_user")
    private String createdUser;
    @Column
    private UUID created;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column
    private UUID     modified;


}
