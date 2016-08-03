package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.models.ClientVehiclesCollection;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;

import java.util.List;

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

    /**
     * Method that gets an instance of the Vehicle Model
     * @param vehicleId the vehicleId
     * @param clientId the clientId
     * @return a instance of the Vehicle Model
     * @throws VehicleServiceException is anything goes wrong
     */
    VehicleDetailsModel getVehicleDetailsModel(String vehicleId,String clientId) throws VehicleServiceException;

    /**
     * Method to add of update a Vehicle
     * @param vehicleDetailsModel the Vehicle to add or update
     * @return an instance of the Added or Updated Vehicle
     * @throws VehicleServiceException if anything goes wrong
     */
    VehicleDetailsModel upsertVehicle(VehicleDetailsModel vehicleDetailsModel) throws VehicleServiceException;

    /**
     * Method that gets a list of client vehicles
     * @param clientId the client id
     * @return an array of client vehicles based off client id
     * @throws VehicleServiceException is anything goes wrong
     */
    ClientVehiclesCollection getClientVehicles(String clientId) throws VehicleServiceException;
}
