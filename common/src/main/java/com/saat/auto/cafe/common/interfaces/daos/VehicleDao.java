package com.saat.auto.cafe.common.interfaces.daos;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleCollection;
import com.saat.auto.cafe.common.entitys.VehicleImage;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;

import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

/**
 * Created by mcoletti on 5/9/16.
 */
public interface VehicleDao{

    /**
     * Method that add a New Vehicle image
     * @param vi the VehicleImage object to add
     * @return the newly create Vehicle image Object
     */
    VehicleImage insertVehicleImage(VehicleImage vi) throws ClientVehicleException;

    /**
     * Method to get a list of the Vehicle Images
     * @param dealershipId the dealership
     * @param stockNum the stock number
     * @return an array of VehicleImage for the given Vehicle Id
     */
    VehicleImage getVehicleImage(UUID dealershipId,String stockNum) throws ClientVehicleException;

    /**
     * Method that adds or updates a DealerShip Vehicle into the DB
     * @param cv the DealerShip Vehicle Object to Add/Update
     * @return the newly added or updated DealerShip Vehicle oject
     * @throws ClientVehicleException is anything goes wrong
     */
    Vehicle upsert(Vehicle cv) throws ClientVehicleException;

    /**
     * Method to get a list of DealerShip Vehicle by DealerShip Id
     * @param dealerId the DealerShip Id
     * @return and Array of Vehicle
     * @throws ClientVehicleException if anything goes wrong
     */
    VehicleCollection get(UUID dealerId) throws ClientVehicleException;
//    List<Vehicle> get(UUID dealerId) throws ClientVehicleException;



    /**
     * Method to get a DealerShip Vehicle from the DB based off dealerId and dealershipId
     * @param dealerId the client UUID
     * @param stockNum the vehicle StockNum
     * @return an instance of the Vehicle object
     * @throws ClientVehicleException if anything goes wrong
     */
    Vehicle get(UUID dealerId, String stockNum) throws ClientVehicleException;




}
