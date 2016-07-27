package com.saat.auto.cafe.service;

import com.saat.auto.cafe.common.entitys.VehicleDetails;
import com.saat.auto.cafe.common.exceptions.VehicleDetailsException;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.VehicleDao;
import com.saat.auto.cafe.common.interfaces.VehicleService;
import com.saat.auto.cafe.common.models.VehicleDetailsModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by micahcoletti on 7/27/16.
 */
public class VehicleServiceImpl implements VehicleService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CacheService cacheService;
    private final VehicleDao vehicleDao;

    /**
     * Main COnstructor
     */
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
            //Add model to cache
            cacheService.insertCacheEntry(vdm.getId(), vdm);
        } catch (VehicleDetailsException e) {
            e.printStackTrace();
            log.error("Error adding new Vehicle for ClientId: {} and VehicleId: {}", vehicleDetailsModel.getClientId(), vehicleDetailsModel.getId());
            throw new VehicleServiceException(e);
        }


        return vdm;
    }


}
