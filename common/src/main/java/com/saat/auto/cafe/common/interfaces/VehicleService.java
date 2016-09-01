package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.models.VehicleModelCollection;
import com.saat.auto.cafe.common.models.VehicleModel;

/**
 * Created by micahcoletti on 7/27/16.
 */
public interface VehicleService {

//    /**
//     * Method to get an Instance of VehicleModel
//     * @param vehicleId the vehicleId to get from the backend
//     * @param dealerId the dealerId
//     * @return an instance of the VehicleDetailModel
//     */
//    VehicleDetailModel getVehicleDetailsModel(String vehicleId, String dealerId, boolean resetCache) throws VehicleServiceException;
//
//    /**
//     * Method that gets an instance of the Vehicle Model
//     * @param vehicleId the vehicleId
//     * @param dealerId the dealerId
//     * @return a instance of the Vehicle Model
//     * @throws VehicleServiceException is anything goes wrong
//     */
//    VehicleDetailModel getVehicleDetailsModel(String vehicleId, String dealerId) throws VehicleServiceException;
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
//     * @param dealerId the client id
//     * @return an array of client vehicles based off client id
//     * @throws VehicleServiceException is anything goes wrong
//     */
//    VehicleModelCollection getVehicles(String dealerId) throws VehicleServiceException;


    VehicleModel upsertDealerShipVehicle(VehicleModel cv) throws VehicleServiceException;

    VehicleModelCollection get(String dealerId) throws VehicleServiceException;
    VehicleModelCollection get(String dealerId, boolean resetCache) throws VehicleServiceException;

    VehicleModel get(String dealerId, String stockNum) throws VehicleServiceException;
    VehicleModel get(String dealerId, String stockNum, boolean resetCache) throws VehicleServiceException;


}
