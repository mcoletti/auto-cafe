package com.saat.auto.cafe.common.interfaces.daos;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleImage;
import com.saat.auto.cafe.common.exceptions.VehicleDaoException;

import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

/**
 * Created by mcoletti on 5/9/16.
 */
public interface VehicleDao {

    /**
     * Method that add a New Vehicle image
     *
     * @param vi the VehicleImage object to add
     * @return the newly create Vehicle image Object
     */
    VehicleImage insertVehicleImage(VehicleImage vi) throws VehicleDaoException;

    /**
     * Method to get a list of the Vehicle Images
     *
     * @param dealershipId the dealership
     * @param stockNum     the stock number
     * @return an array of VehicleImage for the given Vehicle Id
     */
    VehicleImage getVehicleImage(UUID dealershipId, String stockNum) throws VehicleDaoException;

    /**
     * Method that adds or updates a DealerShip Vehicle into the DB
     *
     * @param cv the DealerShip Vehicle Object to Add/Update
     * @return the newly added or updated DealerShip Vehicle oject
     * @throws VehicleDaoException is anything goes wrong
     */
    Vehicle upsert(Vehicle cv) throws VehicleDaoException;

    /**
     * Method to get a list of DealerShip Vehicle by DealerShip Id
     *
     * @param dealerId the DealerShip Id
     * @return and Array of Vehicle
     * @throws VehicleDaoException if anything goes wrong
     */
    List<Vehicle> get(UUID dealerId) throws VehicleDaoException;
//    List<Vehicle> get(UUID dealerId) throws VehicleDaoException;


    /**
     * Method to get a DealerShip Vehicle from the DB based off dealerId and dealershipId
     *
     * @param dealerId  the client UUID
     * @param vehicleId the vehicle StockNum
     * @return an instance of the Vehicle object
     * @throws VehicleDaoException if anything goes wrong
     */
    Vehicle get(UUID dealerId, UUID vehicleId) throws VehicleDaoException;


    Vehicle getByVin(String vin) throws VehicleDaoException;

    Vehicle getByStockNum(String stockNum) throws VehicleDaoException;
}
