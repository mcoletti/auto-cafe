package com.saat.auto.cafe.common.entitys;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 8/10/16.
 */
@Builder
@Data
public class ClientVehicleCollection {


    private List<ClientVehicle> clientVehicles;

}
