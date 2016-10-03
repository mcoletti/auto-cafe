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
    @Column(name = "dealership_id")
    private UUID dealershipId;
    @Column(name = "stock_num")
    private String stockNum;
    @Column(name = "img_url")
    private String imgypeUrl;
    @Column(name = "img_type")
    private String imgType;
    @Column(name = "img_order")
    private int imgOrder;



}
