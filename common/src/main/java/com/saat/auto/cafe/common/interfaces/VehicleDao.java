package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleInvException;
import com.saat.auto.cafe.common.models.CatalogImage;
import com.saat.auto.cafe.common.models.VehicleDetails;

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
     * @throws VehicleInvException if anything goes wrong
     */
    VehicleDetails upsertVehicleDetails(VehicleDetails vi) throws VehicleInvException;

    /**
     * Method to get a Vehicle Inventory record based off Id
     * @param vehicleId the Id of the Inventory record
     * @return an instance of the VehicleDetails record
     * @throws VehicleInvException is anything goes wrong
     */
    VehicleDetails get(UUID vehicleId) throws VehicleInvException;

    /**
     * Method to get a Vehicle Inventory record based off keyName
     * @param keyName the keyName
     * @return an instance of the VehicleDetails record
     * @throws VehicleInvException is anything goes wrong
     */
    VehicleDetails get(String keyName) throws VehicleInvException;

    List<VehicleDetails> getByClientId(UUID clientId);
    /**
     * Gets a list of catalog images based of Id
     *
     * @param vehicleInventoryId the unique Id in the inventory Table
     */
    List<CatalogImage> getCatalogImages(int vehicleInventoryId) throws VehicleInvException;

    /**
     * Get a list of catlog images based off the KeyName
     *
     * @param vehicleKeyName the Vehicle KeyName ex: 2010 BMW 3-Series
     */
    List<CatalogImage> getCatalogImages(String vehicleKeyName) throws VehicleInvException;



}
