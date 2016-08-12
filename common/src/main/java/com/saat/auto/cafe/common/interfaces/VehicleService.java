package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.models.ClientVehicleCollectionModel;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;

/**
 * Created by micahcoletti on 7/27/16.
 */
public interface VehicleService {

//    /**
//     * Method to get an Instance of VehicleModel
//     * @param vehicleId the vehicleId to get from the backend
//     * @param clientId the clientId
//     * @return an instance of the VehicleDetailModel
//     */
//    VehicleDetailModel getVehicleDetailsModel(String vehicleId, String clientId, boolean resetCache) throws VehicleServiceException;
//
//    /**
//     * Method that gets an instance of the Vehicle Model
//     * @param vehicleId the vehicleId
//     * @param clientId the clientId
//     * @return a instance of the Vehicle Model
//     * @throws VehicleServiceException is anything goes wrong
//     */
//    VehicleDetailModel getVehicleDetailsModel(String vehicleId, String clientId) throws VehicleServiceException;
//
//    /**
//     * Method to add of update a Vehicle
//     * @param vehicleDetailModel the Vehicle to add or update
//     * @return an instance of the Added or Updated Vehicle
//     * @throws VehicleServiceException if anything goes wrong
//     */
//    VehicleDetailModel upsertVehicle(VehicleDetailModel vehicleDetailModel) throws VehicleServiceException;
//
//    /**
//     * Method that gets a list of client vehicles
//     * @param clientId the client id
//     * @return an array of client vehicles based off client id
//     * @throws VehicleServiceException is anything goes wrong
//     */
//    ClientVehicleCollectionModel getClientVehicles(String clientId) throws VehicleServiceException;


    ClientVehiclesModel upsertClientVehicle(ClientVehiclesModel cv) throws VehicleServiceException;

    ClientVehicleCollectionModel get(String clientId) throws VehicleServiceException;
    ClientVehicleCollectionModel get(String clientId, boolean resetCache) throws VehicleServiceException;

    ClientVehiclesModel get(String clientId,String vehicleId) throws VehicleServiceException;
    ClientVehiclesModel get(String clientId,String vehicleId, boolean resetCache) throws VehicleServiceException;


}
