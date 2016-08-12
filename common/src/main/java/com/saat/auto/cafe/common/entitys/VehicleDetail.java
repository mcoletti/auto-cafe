package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import com.saat.auto.cafe.common.models.VehicleDetailModel;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mcoletti on 5/10/16.
 */
@UDT(keyspace = "autocafe", name = "vehicle_detail")
@NoArgsConstructor
@Data
public class VehicleDetail {

    @Field(name = "ext_color")
    private String extColor;
    @Field(name = "int_color")
    private String intColor;
    @Field
    private String trim;
    @Field(name = "body_style")
    private String bodyStyle;
    @Field
    private int year;
    @Field
    private String make;
    @Field
    private String model;
    @Field
    private double mileage;

    public VehicleDetailModel toModel(){


        return VehicleDetailModel.builder()
                .bodyStyle(bodyStyle).intColor(intColor)
                .trim(trim).year(year)
                .make(make).model(model)
                .mileage(mileage).build();
    }
}

