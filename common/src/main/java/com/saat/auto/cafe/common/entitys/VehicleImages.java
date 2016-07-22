package com.saat.auto.cafe.common.entitys;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * Created by micahcoletti on 7/18/16.
 */
@Table(value = "vehicle_images")
@Builder
public class VehicleImages {


    @PrimaryKey(value= "vehicle_id")
    @Getter
    private UUID vehicleId;
    @Column(value = "img_type")
    private String imgType;
    @Column(value = "img_name")
    private String imgName;
    @Column(value = "img_cdn_loc")
    private String imgCdnLoc;
    @Column(value = "img_size")
    private int imgSize;
    @Column(value = "img_suffix")
    private String imgSuffix;
    @Column(value = "img_order")
    private int imgOrder;



}
