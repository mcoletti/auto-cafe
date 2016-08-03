package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.ClientVehicles;
import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.exceptions.VehicleDetailsException;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.ClientVehiclesCollection;
import com.saat.auto.cafe.common.models.ClientVehiclesModel;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;

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
    public VehicleDetailsModel getVehicleDetailsModel(String vehicleId, String clientId, boolean resetCache) throws VehicleServiceException {
        // Check to see if the Vehicle is in Cache First
        log.debug("Getting Vehicle Details for VehicleId: {} and ClientId: {}", vehicleId, clientId);
        VehicleDetailsModel vdm = null;
        try {

            // Check if the reset cache flag has been set
            if (resetCache) {
                log.debug("The ResetCache flag was set... clearing out cache for Key: {}", vehicleId);
                cacheService.removeEntry(vehicleId);
            } else {
                vdm = cacheService.getCacheEntry(vehicleId, VehicleDetailsModel.class);
            }
            if (vdm == null) {

                VehicleDetails vd = vehicleDao.get(UUID.fromString(vehicleId), UUID.fromString(clientId));
                vdm = vd.toModel();
                cacheService.insertCacheEntry(vdm.getId(),vdm,VehicleDetailsModel.class);
            }
        } catch (VehicleDetailsException e) {
            e.printStackTrace();
            log.error("Error getting Vehicle Details for VehicleId: {} and ClientId: {} - []", vehicleId, clientId, e.getMessage());
            throw new VehicleServiceException(e);
        }
        return vdm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleDetailsModel getVehicleDetailsModel(String vehicleId, String clientId) throws VehicleServiceException {

        return getVehicleDetailsModel(vehicleId, clientId, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleDetailsModel upsertVehicle(VehicleDetailsModel vehicleDetailsModel) throws VehicleServiceException {

        log.debug("Adding/Updating new Vehicle for clientId: {} vehicleId: {}", vehicleDetailsModel.getClientId(), vehicleDetailsModel.getId());
        VehicleDetailsModel vdm;
        try {
            VehicleDetails vd = vehicleDao.upsertVehicleDetails(vehicleDetailsModel.toEntity());
            vdm = vd.toModel();
            //Flush and Add model to cache
            cacheService.removeEntry(vdm.getId());  // Flush the vehicle entry cache for key
            cacheService.removeEntry(ClientVehiclesCollection.getCollectionCacheKey(vdm.getClientId())); // Flush the Client Vehicles Collection based off ClientId
            cacheService.insertCacheEntry(vdm.getId(), vdm,VehicleDetailsModel.class);
        } catch (VehicleDetailsException e) {
            e.printStackTrace();
            log.error("Error adding new Vehicle for ClientId: {} and VehicleId: {}", vehicleDetailsModel.getClientId(), vehicleDetailsModel.getId());
            throw new VehicleServiceException(e);
        }


        return vdm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientVehiclesCollection getClientVehicles(String clientId) throws VehicleServiceException {

        ClientVehiclesCollection collection;
        String cacheKey = ClientVehiclesCollection.getCollectionCacheKey(clientId);
        try {

            // Check to see if it is in Cache
            log.debug("Getting the Client Vehicles Collection from Cache for ClientId: {}",clientId);
            collection = cacheService.getCacheEntry(cacheKey,ClientVehiclesCollection.class);

            if(collection == null){
                log.debug("Collection not in Cache...Getting a collection of client vehicles for client id: {} from DB",clientId);

                List<ClientVehicles> clientVehiclesList = vehicleDao.getByClientId(UUID.fromString(clientId));
                List<ClientVehiclesModel> cvmList = new ArrayList<>();
                clientVehiclesList.forEach(cv -> {
                    cvmList.add(cv.toModel());

                });

                collection = ClientVehiclesCollection.builder().clientVehicles(cvmList).build();

                // Add to Cache
                if(collection != null){

                    cacheService.removeEntry(cacheKey);
                    cacheService.insertCacheEntry(cacheKey,collection,ClientVehiclesCollection.class);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        }


        return collection;
    }


}
