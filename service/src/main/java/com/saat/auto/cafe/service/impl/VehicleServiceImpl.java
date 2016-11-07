package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.exceptions.VehicleServiceException;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.common.interfaces.services.CacheService;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.common.interfaces.services.VehicleService;
import com.saat.auto.cafe.common.models.DealerShipModel;
import com.saat.auto.cafe.common.models.VehicleModelCollection;
import com.saat.auto.cafe.common.models.VehicleModel;
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
    private final VehicleCacheService cacheService;
    private final VehicleDao vehicleDao;
    private final DealerShipDao dealerShipDao;


    /**
     * Main COnstructor
     */
    @Autowired
    public VehicleServiceImpl(VehicleCacheService cacheService, VehicleDao vehicleDao, DealerShipDao dealerShipDao) {

        this.cacheService = cacheService;
        this.vehicleDao = vehicleDao;
        this.dealerShipDao = dealerShipDao;
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
            vehicleMakeTotals.put(vehicle.getMake(),makeTotal);
            dealerShip.setMakeVehicleTotals(vehicleMakeTotals);

            // Update DealerShip
            dealerShipDao.upsert(dealerShip.toEntity());

            // Update DealerShip in cache
            cacheService.insertCacheEntry(dealerShip.getId(), dealerShip, DealerShipModel.class);

        } catch (ClientVehicleException e) {
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
