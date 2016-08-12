package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.UDT;
import com.saat.auto.cafe.common.models.VehicleDetailModel;

import org.springframework.data.cassandra.mapping.Column;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mcoletti on 5/10/16.
 */
@UDT(keyspace = "autocafe", name = "vehicle_detail")
@Builder
@Data
public class VehicleDetail {

    @Column("ext_color")
    private String extColor;
    @Column("int_color")
    private String intColor;
    @Column
    private String trim;
    @Column("body_style")
    private String bodyStyle;
    @Column
    private int year;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private double mileage;

    public VehicleDetailModel toModel(){


        return VehicleDetailModel.builder()
                .bodyStyle(bodyStyle).intColor(intColor)
                .trim(trim).year(year)
                .make(make).model(model)
                .mileage(mileage).build();
    }

    public UDTValue toUdtValue(Cluster cluster){

        UDTValue udtValue = cluster.getMetadata().getKeyspace("autocafe").getUserType("vehicle_detail").newValue();

        udtValue.setString("ext_color",extColor)
                .setString("int_color",intColor)
                .setString("trim",trim)
                .setString("body_style",bodyStyle)
                .setInt("year",year)
                .setString("make",make)
                .setString("model",model)
                .setDouble("mileage",mileage);
        return udtValue;
    }

    /**
     * Method to convert from a UDT Type to a Vehicle Entity
     * @param details the UDTValue representing the vehicle details
     * @return a new Instance of the VehicleDetail entity
     */
    public static VehicleDetail fromUdtValue(UDTValue details) {


        return VehicleDetail.builder()
                .extColor(details.getString("ext_color"))
                .intColor(details.getString("int_color"))
                .trim(details.getString("trim"))
                .bodyStyle(details.getString("body_style"))
                .year(details.getInt("year"))
                .make(details.getString("make"))
                .model(details.getString("model"))
                .mileage(details.getDouble("mileage")).build();
    }
}

