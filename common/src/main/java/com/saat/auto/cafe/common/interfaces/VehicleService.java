package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleDetailsException;
import com.saat.auto.cafe.common.models.CatalogImage;
import com.saat.auto.cafe.common.models.VehicleDetails;

import java.util.List;

/**
 * Created by mcoletti on 6/1/16.
 */
public interface VehicleService {


    /**
     * Gets a list of catalog images based of Id
     *
     * @param vehicleInventoryId the unique Id in the inventory Table
     */
    List<CatalogImage> getCatalogImages(int vehicleInventoryId) throws VehicleDetailsException;

    /**
     * Get a list of catlog images based off the KeyName
     *
     * @param vehicleKeyName the Vehicle KeyName ex: 2010 BMW 3-Series
     */
    List<CatalogImage> getCatalogImages(String vehicleKeyName) throws VehicleDetailsException;

    /**
     * Get a Vehicle inventory object based off Id
     */
    VehicleDetails getVehicleInventory(int id) throws VehicleDetailsException;

    /**
     * Get a Vehicle inventory object based off keyName ex: 2010 BMW 3-Series
     *
     * @param keyName the KeyName ex: 2010 BMW 3-Series
     * @return an Vehicle Inventory Object
     */
    VehicleDetails getVehicleInventory(String keyName) throws VehicleDetailsException;

}
