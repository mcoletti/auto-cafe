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
        name = "dealership_lots",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class DealershipLot {

    @PartitionKey
    @Column(name = "dealership_id")
    private UUID dealershipId;
    @Column(name = "lot_id")
    private long lotId;
    @Column(name = "lot_location")
    private String lotLocation;
    @Column
    private UUID created;

}
