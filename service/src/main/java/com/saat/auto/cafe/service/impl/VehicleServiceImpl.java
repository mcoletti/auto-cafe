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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by micahcoletti on 7/27/16.
 */
@Service
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
    public VehicleModel upsertDealerShipVehicle(VehicleModel vehicleModel) throws VehicleServiceException {


        try {
            Vehicle vehicle = vehicleDao.upsert(vehicleModel.toEntity());
            vehicleModel = vehicle.toModel();

            String cacheKey = String.format("%s-%s", vehicleModel.getDealerId(), vehicleModel.getStockNum());
            VehicleModel cacheEntry = cacheService.getCacheEntry(cacheKey, VehicleModel.class);

            if (cacheEntry != null) {
                cacheService.removeEntry(cacheKey);
            }
            cacheService.insertCacheEntry(cacheKey, vehicleModel, VehicleModel.class);

        } catch (ClientVehicleException e) {
            e.printStackTrace();
            log.error("Error on Add/Update of Vehicle - {}", e.getMessage());
            vehicleModel = null;
        }


        return vehicleModel;
    }

    @Override
    public List<VehicleModel> get(String dealerId) throws VehicleServiceException {

        return get(dealerId, false);
    }

    @Override
    public List<VehicleModel> get(String dealerId, boolean resetCache) throws VehicleServiceException {
        VehicleModelCollection cvmc = null;
        String cacheKey = String.format("vehicle-list-%s",dealerId);
        VehicleModel[] vehicleModels = null;
        try {

            if (resetCache) {
                cacheService.removeEntry(cacheKey);
            } else {
                vehicleModels = cacheService.getCacheEntry(cacheKey, VehicleModel[].class);
            }
            if (vehicleModels == null) {
                List<Vehicle> vehicles = vehicleDao.get(UUID.fromString(dealerId));
                List<VehicleModel> modelList = new ArrayList<>();
                vehicles.forEach(vehicle -> modelList.add(vehicle.toModel()));
                vehicleModels = new VehicleModel[modelList.size()];
                vehicleModels = modelList.toArray(vehicleModels);

                cacheService.insertCacheEntry(cacheKey, vehicleModels, VehicleModel[].class);
            }
        } catch (ClientVehicleException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        }
        return Arrays.asList(vehicleModels);
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
            if (cvm == null) {
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
