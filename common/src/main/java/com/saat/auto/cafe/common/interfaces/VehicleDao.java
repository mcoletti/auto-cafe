package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleDetailsException;
import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.entitys.VehicleImages;

import java.util.List;
import java.util.UUID;

/**
 * Created by mcoletti on 5/9/16.
 */
public interface VehicleDao {


    /**
     * Method to Add or Update a Vehicle Inventory record
     * @param vi the Vehicle Inventory record to add or update
     * @return a instance of the Vehicle inventory Record
     * @throws VehicleDetailsException if anything goes wrong
     */
    VehicleDetails upsertVehicleDetails(VehicleDetails vi) throws VehicleDetailsException;

    /**
     * Method to get a Vehicle Inventory record based off Id
     * @param vehicleId the Id of the Inventory record
     * @return an instance of the VehicleDetails record
     * @throws VehicleDetailsException is anything goes wrong
     */
    VehicleDetails get(UUID vehicleId,UUID clientId) throws VehicleDetailsException;

    /**
     * Method to get a Vehicle Inventory record based off keyName
     * @param keyName the keyName
     * @return an instance of the VehicleDetails record
     * @throws VehicleDetailsException is anything goes wrong
     */
    VehicleDetails get(String keyName) throws VehicleDetailsException;

    List<VehicleDetails> getByClientId(UUID clientId);


    /**
     * Method that add a New Vehicle image
     * @param vi the VehicleImages object to add
     * @return the newly create Vehicle image Object
     */
    VehicleImages insertVehicleImage(VehicleImages vi) throws VehicleDetailsException;

    /**
     * Method to get a list of the Vehicle Images
     * @param vehicleId the Vehicle Id to get
     * @return an array of VehicleImages for the given Vehicle Id
     */
    List<VehicleImages> getVehicleImageList(UUID vehicleId) throws VehicleDetailsException;



}
