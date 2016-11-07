package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.MakeVehicleTotalModel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by micahcoletti on 11/2/16.
 */
@UDT(name = "make_vehicle_total", keyspace = "autocafe")
@Data
@NoArgsConstructor
public class MakeVehicleTotal {

    @Field
    private String make;
    @Field(name = "vehicle_total")
    private int vehicleTotal;

    /**
     * Convert to a udt value
     * @param cluster
     * @return
     */
    public UDTValue toUdtValue(Cluster cluster) {

        UDTValue makeVehicleTotalUdt = cluster.getMetadata().getKeyspace(AutoCafeConstants.Schema.KEY_SPACE).getUserType(AutoCafeConstants.UdtTypes.Address.NAME).newValue()
                .setString("make",make)
                .setInt("vehicle_total",vehicleTotal);

        return makeVehicleTotalUdt;
    }

    /**
     * Convert to model object
     * @return
     */
    public MakeVehicleTotalModel toModel(){

        MakeVehicleTotalModel mvtm = new MakeVehicleTotalModel();
        mvtm.setMake(make);
        mvtm.setVehicleTotal(vehicleTotal);
        return mvtm;
    }

}
