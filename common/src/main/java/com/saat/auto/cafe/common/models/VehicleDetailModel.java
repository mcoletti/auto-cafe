package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.VehicleDetail;

import lombok.Builder;
import lombok.Data;

/**
 * Created by mcoletti on 5/10/16.
 */

@Builder
@Data
public class VehicleDetailModel {

    private String bodyStyle;
    private String extColor;
    private String intColor;
    private double mileage;
    private String make;
    private String model;
    private int year;
    private String trim;

    public VehicleDetail toEntity(){
        return VehicleDetail.builder()
                .bodyStyle(bodyStyle)
                .extColor(extColor)
                .intColor(intColor)
                .mileage(mileage)
                .make(make)
                .model(model)
                .year(year)
                .trim(trim).build();
    }


}
