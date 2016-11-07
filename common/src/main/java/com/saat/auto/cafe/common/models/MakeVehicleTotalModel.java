package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.MakeVehicleTotal;

import lombok.Data;

/**
 * Created by micahcoletti on 11/2/16.
 */
@Data
public class MakeVehicleTotalModel {

    private String make;
    private int vehicleTotal;

    /**
     * Convert to Model object
     * @return
     */
    public MakeVehicleTotal toEntity(){

        MakeVehicleTotal mvt = new MakeVehicleTotal();
        mvt.setMake(make);
        mvt.setVehicleTotal(vehicleTotal);
        return mvt;
    }

}
