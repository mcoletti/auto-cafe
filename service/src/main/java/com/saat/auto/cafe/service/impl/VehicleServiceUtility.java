package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.common.models.DealerShipModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by micahcoletti on 11/7/16.
 */
@Service
public class VehicleServiceUtility {


    private final VehicleDao vehicleDao;
    private final DealerShipDao dealerShipDao;

    @Autowired
    public VehicleServiceUtility(VehicleDao vehicleDao, DealerShipDao dealerShipDao) {
        this.vehicleDao = vehicleDao;
        this.dealerShipDao = dealerShipDao;
    }


    public void updateVehicleMakeTotals(String dealerShipId) throws VehicleServiceException {

        try {

            // Get the List of Vehicle for Dealer
            List<Vehicle> vehicleList = vehicleDao.get(UUID.fromString(dealerShipId));

            List<String> makeList = new ArrayList<>();
            vehicleList.forEach(vehicle -> {
                makeList.add(vehicle.getMake());
            });

            Set<String> distinctMakeList = new HashSet<>(makeList);

            Map<String, Integer> vehicleMakeTotalMap = new HashMap<>();
            distinctMakeList.forEach(s -> {
                int makeTotal = getVehicleMakeTotal(s, vehicleList);
                vehicleMakeTotalMap.put(s, makeTotal);
            });

            DealerShipModel dealerShip = dealerShipDao.get(UUID.fromString(dealerShipId)).toModel();
            dealerShip.setMakeVehicleTotals(vehicleMakeTotalMap);

            dealerShipDao.upsert(dealerShip.toEntity());

        } catch (ClientVehicleException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);

        }


    }


    private int getVehicleMakeTotal(String make, List<Vehicle> vehicleList) {

        final int[] makeTotal = {0};
        vehicleList.forEach(vehicle -> {

            if (vehicle.getMake().toUpperCase().equals(make.toUpperCase())) {
                makeTotal[0]++;
            }

        });
        return makeTotal[0];

    }

    private boolean checkIfMakeIsInList(String vehicleMake, List<Vehicle> vehicleList) {


        final boolean[] inList = {false};
        vehicleList.forEach(vehicle -> {

            if (vehicle.getMake().toUpperCase().equals(vehicleMake.toUpperCase())) {
                inList[0] = true;
                return;
            }
        });
        return inList[0];
    }


}
