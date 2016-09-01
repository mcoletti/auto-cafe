package com.saat.auto.cafe.common.interfaces;

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


//    /**
//     * Method to Add or Update a Vehicle Inventory record
//     * @param vi the Vehicle Inventory record to add or update
//     * @return a instance of the Vehicle inventory Record
//     * @throws ClientVehicleException if anything goes wrong
//     */
//    VehicleDetail upsertVehicleDetails(VehicleDetail vi) throws ClientVehicleException;

//    /**
//     * Method to get a Vehicle Inventory record based off Id
//     * @param vehicleId the Id of the Inventory record
//     * @return an instance of the VehicleDetailModel record
//     * @throws ClientVehicleException is anything goes wrong
//     */
//    VehicleDetail get(UUID vehicleId, UUID dealerId) throws ClientVehicleException;

//    /**
//     * Method to get a Vehicle Inventory record based off keyName
//     * @param keyName the keyName
//     * @return an instance of the VehicleDetailModel record
//     * @throws ClientVehicleException is anything goes wrong
//     */
//    VehicleDetail get(String keyName) throws ClientVehicleException;
//
//    List<Vehicle> qryByDealerId(UUID dealerId);


    /**
     * Method that add a New Vehicle image
     * @param vi the VehicleImage object to add
     * @return the newly create Vehicle image Object
     */
    VehicleImage insertVehicleImage(VehicleImage vi) throws ClientVehicleException;

    /**
     * Method to get a list of the Vehicle Images
     * @param vehicleId the Vehicle Id to get
     * @return an array of VehicleImage for the given Vehicle Id
     */
    List<VehicleImage> getVehicleImageList(UUID vehicleId) throws ClientVehicleException;

    /**
     * Method that adds or updates a DealerShip Vehicle into the DB
     * @param cv the DealerShip Vehicle Object to Add/Update
     * @return the newly added or updated DealerShip Vehicle oject
     * @throws ClientVehicleException is anything goes wrong
     */
    Vehicle upsertClientVehicle(Vehicle cv) throws ClientVehicleException;

    /**
     * Method to get a list of DealerShip Vehicle by DealerShip Id
     * @param dealerId the DealerShip Id
     * @return and Array of Vehicle
     * @throws ClientVehicleException if anything goes wrong
     */
    VehicleCollection get(UUID dealerId) throws ClientVehicleException;
//    List<Vehicle> get(UUID dealerId) throws ClientVehicleException;



    /**
     * Method to get a DealerShip Vehicle from the DB based off dealerId and vehicleId
     * @param dealerId the client UUID
     * @param stockNum the vehicle StockNum
     * @return an instance of the Vehicle object
     * @throws ClientVehicleException if anything goes wrong
     */
    Vehicle get(UUID dealerId, String stockNum) throws ClientVehicleException;




}
