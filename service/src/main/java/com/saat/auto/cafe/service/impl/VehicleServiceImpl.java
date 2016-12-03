package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.exceptions.VehicleDaoException;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.common.interfaces.services.VehicleService;
import com.saat.auto.cafe.common.models.DealerShipModel;
import com.saat.auto.cafe.common.models.VehicleModelCollection;
import com.saat.auto.cafe.common.models.VehicleModel;
import com.saat.auto.cafe.data.dao.DaoFactory;
import com.saat.auto.cafe.service.cache.VehicleCacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by micahcoletti on 7/27/16.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final VehicleDao vehicleDao;
    private final DealerShipDao dealerShipDao;
    private final VehicleCacheService cacheService;

    /**
     * Main COnstructor
     */

    @Autowired
    public VehicleServiceImpl(DaoFactory daoFactory, VehicleCacheService cacheService) {
        this.vehicleDao = daoFactory.getVehicleDao();
        this.dealerShipDao = daoFactory.getDealerShipDao();
        this.cacheService = cacheService;
    }

    @Override
    public VehicleModel upsertDealerShipVehicle(VehicleModel vehicleModel) throws VehicleServiceException {


        try {
            String cacheKey = String.format("%s-%s", vehicleModel.getDealerId(), vehicleModel.getStockNum());

            log.debug("Adding/Updating new Vehicle to the system");
            Vehicle vehicle = vehicleDao.upsert(vehicleModel.toEntity());
            vehicleModel = vehicle.toModel();

            log.debug("Adding Vehicle to Cache");
            cacheService.insertCacheEntry(cacheKey, vehicleModel, VehicleModel.class);

            // Clear out the Vehicle List Cache for DealerShip
            log.debug("clear out the Vehicle Cache");
            String cacheKeyDealer = String.format("vehicle-list-%s", vehicleModel.getDealerId());
            cacheService.removeEntry(cacheKeyDealer);

            // Updated the Make vehicle total for a Dealership
            log.debug("Update the Make Vehicle Totals for the Vehicle DealerShip");
            DealerShipModel dealerShip = dealerShipDao.get(UUID.fromString(vehicleModel.getDealerId())).toModel();
            int makeTotal = 0;
            Map<String, Integer> vehicleMakeTotals = dealerShip.getMakeVehicleTotals() != null ? dealerShip.getMakeVehicleTotals() : new HashMap<>();
            if (vehicleMakeTotals.containsKey(vehicle.getMake())) {
                makeTotal = vehicleMakeTotals.get(vehicle.getMake());
                makeTotal++;
                vehicleMakeTotals.remove(vehicle.getMake());
            } else {
                makeTotal++;
            }
            vehicleMakeTotals.put(vehicle.getMake(), makeTotal);
            dealerShip.setMakeVehicleTotals(vehicleMakeTotals);

            // Update DealerShip
            dealerShipDao.upsert(dealerShip.toEntity());

            // Update DealerShip in cache
            cacheService.insertCacheEntry(dealerShip.getId(), dealerShip, DealerShipModel.class);

        } catch (VehicleDaoException e) {
            e.printStackTrace();
            log.error("Error on Add/Update of Vehicle - {}", e.getMessage());
            vehicleModel = null;
        } catch (DaoException e) {
            e.printStackTrace();
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
        String cacheKey = String.format("vehicle-list-%s", dealerId);
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
                // Clear out all Vehicle Cache
                vehicles.forEach(vehicle -> resetAllVehicleCache(vehicle.toModel()));
                vehicles.forEach(vehicle -> modelList.add(vehicle.toModel()));
                vehicleModels = new VehicleModel[modelList.size()];
                vehicleModels = modelList.toArray(vehicleModels);
                cacheService.insertCacheEntry(cacheKey, vehicleModels, VehicleModel[].class);
            }
        } catch (VehicleDaoException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        }
        return Arrays.asList(vehicleModels);
    }


    @Override
    public VehicleModel get(String dealerId, String vehicleId, boolean resetCache) throws VehicleServiceException {
        VehicleModel vehicle = null;
        String cacheKey = String.format("%s-%s", dealerId, vehicleId);
        try {

            if (resetCache) {
                cacheService.removeEntry(cacheKey);
            } else {
                vehicle = cacheService.getCacheEntry(cacheKey, VehicleModel.class);
            }
            if (vehicle == null) {
                Vehicle cv = vehicleDao.get(UUID.fromString(dealerId), UUID.fromString(vehicleId));
                if (cv != null) {
                    vehicle = cv.toModel();
                    resetAllVehicleCache(vehicle);
                    cacheService.insertCacheEntry(cacheKey, vehicle, VehicleModel.class);
                }

            }
        } catch (VehicleDaoException e) {
            e.printStackTrace();
            throw new VehicleServiceException(e);
        }
        return vehicle;
    }

    @Override
    public VehicleModel getByVin(String vin) throws VehicleServiceException {
        return getByVin(vin, false);
    }

    @Override
    public VehicleModel getByVin(String vin, boolean resetCache) throws VehicleServiceException {

        log.debug("Getting Vehicle by VIN {}", vin);
        VehicleModel vehicle = null;
        try {
            String cacheKey = String.format("%s", vin);

            if (resetCache) {
                cacheService.removeEntry(cacheKey);
            } else {
                vehicle = cacheService.getCacheEntry(cacheKey, VehicleModel.class);
            }

            if (vehicle == null) {

                Vehicle vehicleEntity = vehicleDao.getByVin(vin);
                if (vehicleEntity != null) {
                    vehicle = vehicleEntity.toModel();
                    resetAllVehicleCache(vehicle);
                    cacheService.insertCacheEntry(cacheKey, vehicle, VehicleModel.class);
                }
            }
        } catch (VehicleDaoException e) {
            log.error("Error getting Vehicle by VIN {} - {}", vin, e.getMessage());
        }


        return vehicle;
    }

    @Override
    public VehicleModel getByStockNum(String stockNum) throws VehicleServiceException {
        return getByStockNum(stockNum,false);
    }

    @Override
    public VehicleModel getByStockNum(String stockNum, boolean resetCache) throws VehicleServiceException {
        log.debug("Getting Vehicle by StockNum {}", stockNum);
        VehicleModel vehicle = null;
        try {
            String cacheKey = String.format("%s", stockNum);

            if (resetCache) {
                cacheService.removeEntry(cacheKey);
            } else {
                vehicle = cacheService.getCacheEntry(cacheKey, VehicleModel.class);
            }

            if (vehicle == null) {

                Vehicle vehicleEntity = vehicleDao.getByStockNum(stockNum);
                if (vehicleEntity != null) {
                    vehicle = vehicleEntity.toModel();
                    resetAllVehicleCache(vehicle);
                    cacheService.insertCacheEntry(cacheKey, vehicle, VehicleModel.class);
                }
            }
        } catch (VehicleDaoException e) {
            log.error("Error getting Vehicle by Stock Num {} - {}", stockNum, e.getMessage());
        }


        return vehicle;
    }


    private void resetAllVehicleCache(VehicleModel vehicleModel){


        String baseCacheKey = String.format("%s-%s", vehicleModel.getDealerId(), vehicleModel.getStockNum());
        cacheService.removeEntry(baseCacheKey);

        String vinCacheKey = String.format("%s", vehicleModel.getVin());
        cacheService.removeEntry(vinCacheKey);

        String stocNumCacheKey = String.format("%s", vehicleModel.getStockNum());
        cacheService.removeEntry(stocNumCacheKey);

        String dealerShipcacheKey = String.format("vehicle-list-%s", vehicleModel.getDealerId());
        cacheService.removeEntry(dealerShipcacheKey);

    }


}
