package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.entitys.ClientVehicle;
import com.saat.auto.cafe.common.entitys.ClientVehicleCollection;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.entitys.VehicleImages;
import com.saat.auto.cafe.common.models.ClientVehicleCollectionModel;

import java.util.List;
import java.util.UUID;

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
//    VehicleDetail get(UUID vehicleId, UUID clientId) throws ClientVehicleException;

//    /**
//     * Method to get a Vehicle Inventory record based off keyName
//     * @param keyName the keyName
//     * @return an instance of the VehicleDetailModel record
//     * @throws ClientVehicleException is anything goes wrong
//     */
//    VehicleDetail get(String keyName) throws ClientVehicleException;
//
//    List<ClientVehicle> getByClientId(UUID clientId);


    /**
     * Method that add a New Vehicle image
     * @param vi the VehicleImages object to add
     * @return the newly create Vehicle image Object
     */
    VehicleImages insertVehicleImage(VehicleImages vi) throws ClientVehicleException;

    /**
     * Method to get a list of the Vehicle Images
     * @param vehicleId the Vehicle Id to get
     * @return an array of VehicleImages for the given Vehicle Id
     */
    List<VehicleImages> getVehicleImageList(UUID vehicleId) throws ClientVehicleException;

    /**
     * Method that adds or updates a Client Vehicle into the DB
     * @param cv the Client Vehicle Object to Add/Update
     * @return the newly added or updated Client Vehicle oject
     * @throws ClientVehicleException is anything goes wrong
     */
    ClientVehicle upsetClientVehicle(ClientVehicle cv) throws ClientVehicleException;

    /**
     * Method to get a list of Client Vehicle by Client Id
     * @param clientId the Client Id
     * @return and Array of ClientVehicle
     * @throws ClientVehicleException if anything goes wrong
     */
    ClientVehicleCollection get(UUID clientId) throws ClientVehicleException;
//    List<ClientVehicle> get(UUID clientId) throws ClientVehicleException;



    /**
     * Method to get a Client Vehicle from the DB based off clientId and vehicleId
     * @param clientId the client UUID
     * @param vehicleId the vehicle UUID
     * @return an instance of the ClientVehicle object
     * @throws ClientVehicleException if anything goes wrong
     */
    ClientVehicle get(UUID clientId, UUID vehicleId) throws ClientVehicleException;


}
