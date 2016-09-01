package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;


import java.util.UUID;

import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 7/18/16.
 */
@Table(name = "vehicle_images")
@Builder
@Data
public class VehicleImage {


    @PartitionKey
    @Column(name = "vehicle_id")
    private UUID vehicleId;
    @Column(name = "img_type")
    private String imgType;
    @Column(name = "img_name")
    private String imgName;
    @Column(name = "img_cdn_loc")
    private String imgCdnLoc;
    @Column(name = "img_size")
    private int imgSize;
    @Column(name = "img_suffix")
    private String imgSuffix;
    @Column(name = "img_order")
    private int imgOrder;



}
