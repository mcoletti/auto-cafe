package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.ClientVehicle;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.ClientVehicleCollectionModel;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by micahcoletti on 7/27/16.
 */
@Component
public class VehicleServiceImpl implements VehicleService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private CacheService cacheService;
    private VehicleDao vehicleDao;



    /**
     * Main COnstructor
     */
    @Autowired
    public VehicleServiceImpl(CacheService cacheService, VehicleDao vehicleDao) {

        this.cacheService = cacheService;
        this.vehicleDao = vehicleDao;
    }

    @Override
    public ClientVehiclesModel upsertClientVehicle(ClientVehiclesModel cvm) throws VehicleServiceException {


        try {
            ClientVehicle cv = vehicleDao.upsetClientVehicle( cvm.toEntity());
            cvm = cv.toModel();
        } catch (ClientVehicleException e) {
            e.printStackTrace();
            cvm = null;
        }


        return cvm;
    }

    @Override
    public ClientVehicleCollectionModel get(String clientId) throws VehicleServiceException {

       return get(clientId,false);
    }

    @Override
    public ClientVehicleCollectionModel get(String clientId, boolean resetCache) throws VehicleServiceException {
//        List<ClientVehiclesModel> modelList = new ArrayList<>();
//        ClientVehicleCollectionModel colection = null;
//        String cacheKey = ClientVehicleCollectionModel.getCollectionCacheKey(clientId);
//        try {
//
//            if(resetCache){
//                cacheService.removeEntry(cacheKey);
//            }
//
//            colection = cacheService.getCacheEntry(cacheKey,ClientVehicleCollectionModel.class);
//            if(colection == null){
//                List<ClientVehicle> clientVehicles = vehicleDao.get(UUID.fromString(clientId));
//                clientVehicles.forEach(cv -> modelList.add(cv.toModel()));
//                colection = ClientVehicleCollectionModel.builder()
//                        .clientVehicles(modelList).build();
//                cacheService.insertCacheEntry(cacheKey,colection,ClientVehicleCollectionModel.class);
//            }
//
//
//        } catch (ClientVehicleException e) {
//            e.printStackTrace();
//        }



        return null;
    }

    @Override
    public ClientVehiclesModel get(String clientId, String vehicleId) throws VehicleServiceException {

        ClientVehiclesModel cvm;
        String cacheKey = String.format("%s-%s",clientId,vehicleId);
        try {


            cvm = cacheService.getCacheEntry(cacheKey,ClientVehiclesModel.class);

            if(cvm == null){
                ClientVehicle cv = vehicleDao.get(UUID.fromString(clientId),UUID.fromString(vehicleId));
                cvm = cv.toModel();
                cacheService.insertCacheEntry(cacheKey,cvm,ClientVehiclesModel.class);
            }
        } catch (ClientVehicleException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        }
        return cvm;
    }

    @Override
    public ClientVehiclesModel get(String clientId, String vehicleId, boolean resetCache) throws VehicleServiceException {
        return null;
    }


//    @Override
//    public VehicleDetailModel getVehicleDetailsModel(String vehicleId, String clientId, boolean resetCache) throws VehicleServiceException {
//        // Check to see if the Vehicle is in Cache First
//        log.debug("Getting Vehicle Details for VehicleId: {} and ClientId: {}", vehicleId, clientId);
//        VehicleDetailModel vdm = null;
//        try {
//
//            // Check if the reset cache flag has been set
//            if (resetCache) {
//                log.debug("The ResetCache flag was set... clearing out cache for Key: {}", vehicleId);
//                cacheService.removeEntry(vehicleId);
//            } else {
//                vdm = cacheService.getCacheEntry(vehicleId, VehicleDetailModel.class);
//            }
//            if (vdm == null) {
//
//                VehicleDetail vd = vehicleDao.get(UUID.fromString(vehicleId), UUID.fromString(clientId));
//                vdm = vd.toModel();
//                cacheService.insertCacheEntry(vdm.getId(),vdm,VehicleDetailModel.class);
//            }
//        } catch (ClientVehicleException e) {
//            e.printStackTrace();
//            log.error("Error getting Vehicle Details for VehicleId: {} and ClientId: {} - []", vehicleId, clientId, e.getMessage());
//            throw new VehicleServiceException(e);
//        }
//        return vdm;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public VehicleDetailModel getVehicleDetailsModel(String vehicleId, String clientId) throws VehicleServiceException {
//
//        return getVehicleDetailsModel(vehicleId, clientId, false);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public VehicleDetailModel upsertVehicle(VehicleDetailModel vehicleDetailModel) throws VehicleServiceException {
//
//        log.debug("Adding/Updating new Vehicle for clientId: {} vehicleId: {}", vehicleDetailModel.getClientId(), vehicleDetailModel.getId());
//        VehicleDetailModel vdm;
//        try {
//            VehicleDetail vd = vehicleDao.upsertVehicleDetails(vehicleDetailModel.toEntity());
//            vdm = vd.toModel();
//            //Flush and Add model to cache
//            cacheService.removeEntry(vdm.getId());  // Flush the vehicle entry cache for key
//            cacheService.removeEntry(ClientVehicleCollectionModel.getCollectionCacheKey(vdm.getClientId())); // Flush the Client Vehicles Collection based off ClientId
//            cacheService.insertCacheEntry(vdm.getId(), vdm,VehicleDetailModel.class);
//        } catch (ClientVehicleException e) {
//            e.printStackTrace();
//            log.error("Error adding new Vehicle for ClientId: {} and VehicleId: {}", vehicleDetailModel.getClientId(), vehicleDetailModel.getId());
//            throw new VehicleServiceException(e);
//        }
//
//
//        return vdm;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public ClientVehicleCollectionModel getClientVehicles(String clientId) throws VehicleServiceException {
//
//        ClientVehicleCollectionModel collection;
//        String cacheKey = ClientVehicleCollectionModel.getCollectionCacheKey(clientId);
//        try {
//
//            // Check to see if it is in Cache
//            log.debug("Getting the Client Vehicles Collection from Cache for ClientId: {}",clientId);
//            collection = cacheService.getCacheEntry(cacheKey,ClientVehicleCollectionModel.class);
//
//            if(collection == null){
//                log.debug("Collection not in Cache...Getting a collection of client vehicles for client id: {} from DB",clientId);
//
//                List<ClientVehicle> clientVehiclesList = vehicleDao.getByClientId(UUID.fromString(clientId));
//                List<ClientVehiclesModel> cvmList = new ArrayList<>();
//                clientVehiclesList.forEach(cv -> {
//                    cvmList.add(cv.toModel());
//
//                });
//
//                collection = ClientVehicleCollectionModel.builder().clientVehicles(cvmList).build();
//
//                // Add to Cache
//                if(collection != null){
//
//                    cacheService.removeEntry(cacheKey);
//                    cacheService.insertCacheEntry(cacheKey,collection,ClientVehicleCollectionModel.class);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new VehicleServiceException(e);
//        }
//
//
//        return collection;
//    }


}
