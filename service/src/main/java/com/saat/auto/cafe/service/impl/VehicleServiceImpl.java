package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleCollection;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.services.CacheService;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.common.interfaces.services.VehicleService;
import com.saat.auto.cafe.common.models.VehicleModelCollection;
import com.saat.auto.cafe.common.models.VehicleModel;

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
    private final CacheService cacheService;
    private final VehicleDao vehicleDao;


    /**
     * Main COnstructor
     */
    @Autowired
    public VehicleServiceImpl(CacheService cacheService, VehicleDao vehicleDao) {

        this.cacheService = cacheService;
        this.vehicleDao = vehicleDao;
    }

    @Override
    public VehicleModel upsertDealerShipVehicle(VehicleModel cvm) throws VehicleServiceException {


        try {
            Vehicle cv = vehicleDao.upsert(cvm.toEntity());
            cvm = cv.toModel();
        } catch (ClientVehicleException e) {
            e.printStackTrace();
            log.error("Error on Add/Update of Vehicle - {}", e.getMessage());
            cvm = null;
        }


        return cvm;
    }

    @Override
    public VehicleModelCollection get(String dealerId) throws VehicleServiceException {

        return get(dealerId, false);
    }

    @Override
    public VehicleModelCollection get(String dealerId, boolean resetCache) throws VehicleServiceException {
        VehicleModelCollection cvmc = null;
        String cacheKey = VehicleModelCollection.getCollectionCacheKey(dealerId);
        try {

            if(resetCache){
                cacheService.removeEntry(cacheKey);
            }else{
                cvmc = cacheService.getCacheEntry(cacheKey, VehicleModelCollection.class);
            }
            if (cvmc == null){
                VehicleCollection cvl = vehicleDao.get(UUID.fromString(dealerId));
                List<VehicleModel> cvList = new ArrayList<>();

                cvl.getVehicles().forEach(clientVehicle -> {
                    cvList.add(clientVehicle.toModel());
                });
                cvmc = VehicleModelCollection.builder().clientVehicles(cvList).build();

                cacheService.insertCacheEntry(cacheKey, cvmc, VehicleModelCollection.class);
            }
        } catch (ClientVehicleException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        }
        return cvmc;
    }

    @Override
    public VehicleModel get(String dealerId, String stockNum) throws VehicleServiceException {

        return get(dealerId, stockNum, false);
    }

    @Override
    public VehicleModel get(String dealerId, String stockNum, boolean resetCache) throws VehicleServiceException {
        VehicleModel cvm = null;
        String cacheKey = String.format("%s-%s", dealerId, stockNum);
        try {

            if (resetCache) {
                cacheService.removeEntry(cacheKey);
            } else {
                cvm = cacheService.getCacheEntry(cacheKey, VehicleModel.class);
            }
            if(cvm == null){
                Vehicle cv = vehicleDao.get(UUID.fromString(dealerId), stockNum);
                cvm = cv.toModel();
                cacheService.insertCacheEntry(cacheKey, cvm, VehicleModel.class);
            }
        } catch (ClientVehicleException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        }
        return cvm;
    }
}
