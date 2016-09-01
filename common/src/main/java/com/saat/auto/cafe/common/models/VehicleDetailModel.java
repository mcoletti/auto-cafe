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

    /**
     * Convert Model to Entity object
     * @return
     */
    public VehicleDetail toEntity(){

        VehicleDetail vd = new VehicleDetail();
        vd.setExtColor(extColor);
        vd.setIntColor(intColor);
        vd.setTrim(trim);
        vd.setBodyStyle(bodyStyle);
        vd.setYear(year);
        vd.setMake(make);
        vd.setModel(model);
        vd.setMileage(mileage);
        vd.setYear(year);
        return vd;
    }


}
