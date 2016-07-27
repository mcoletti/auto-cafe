package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;

/**
 * Created by micahcoletti on 7/27/16.
 */
public interface VehicleService {

    /**
     * Method to get an Instance of VehicleModel
     * @param vehicleId the vehicleId to get from the backend
     * @param clientId the clientId
     * @return an instance of the VehicleDetailsModel
     */
    VehicleDetailsModel getVehicleDetailsModel(String vehicleId,String clientId,boolean resetCache) throws VehicleServiceException;
    VehicleDetailsModel getVehicleDetailsModel(String vehicleId,String clientId) throws VehicleServiceException;

    /**
     * Method to add of update a Vehicle
     * @param vehicleDetailsModel the Vehicle to add or update
     * @return an instance of the Added or Updated Vehicle
     * @throws VehicleServiceException if anything goes wrong
     */
    VehicleDetailsModel upsertVehicle(VehicleDetailsModel vehicleDetailsModel) throws VehicleServiceException;
}
