package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by micahcoletti on 10/1/16.
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
    @Column
    private UUID id;
    @Column
    private String name;
    @Column(name = "location_detail")
    private LocationDetail locationDetail;
    @Column
    private UUID created;
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column
    private UUID modified;

}
