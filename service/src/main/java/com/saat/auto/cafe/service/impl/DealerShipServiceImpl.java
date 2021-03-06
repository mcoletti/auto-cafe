package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.exceptions.DealershipServiceException;
import com.saat.auto.cafe.common.interfaces.services.CacheService;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.DealerShipModel;
import com.saat.auto.cafe.service.cache.DealershipCacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by micahcoletti on 9/1/16.
 */
@Service
public class DealerShipServiceImpl implements DealerShipService {


    private static final Logger log = LoggerFactory.getLogger(DealerShipServiceImpl.class);

    private final DealershipCacheService cacheService;
    private final DealerShipDao dealerShipDao;

    @Autowired
    public DealerShipServiceImpl(DealershipCacheService cacheService, DealerShipDao dealerShipDao) {
        this.cacheService = cacheService;
        this.dealerShipDao = dealerShipDao;
    }

    @Override
    public void upsertDealserShip(DealerShipModel dealerShipModel) throws DealershipServiceException {

        log.debug("Adding/Updating a dealership {}", dealerShipModel.getName());
        try {
            dealerShipDao.upsert(dealerShipModel.toEntity());
            log.debug("Adding dealership {} to Cache", dealerShipModel.getName());
            cacheService.insertCacheEntry(dealerShipModel.getId(), dealerShipModel, DealerShipModel.class);

            // Remove the Client dealerships cache entry
            String cacheKey = String.format("client-dealerShips-%s",dealerShipModel.getClientId());
            cacheService.removeEntry(cacheKey);

        } catch (DaoException e) {
            e.printStackTrace();
            log.debug("Error adding or updating the dealership entity for Dealership {} - {}", dealerShipModel.getName(), e.getMessage());
            throw new DealershipServiceException(e);
        }

        log.debug("dealership added/updated for {}", dealerShipModel.getName());
    }

    @Override
    public DealerShipModel get(String dealerId) throws DealershipServiceException {

        log.debug("Getting dealership for Id {}", dealerId);
        DealerShipModel model = null;
        try {

            log.debug("Seeing if with have the dealership in Cache for Id {}", dealerId);
            model = cacheService.getCacheEntry(dealerId, DealerShipModel.class);
            if (model == null) {

                log.debug("dealership for Id {} was not found in Cache", dealerId);
                DealerShip dealerShip = dealerShipDao.get(UUID.fromString(dealerId));
                model = dealerShip.toModel();
                log.debug("adding dealership {} into Cache", model.getName());
                cacheService.insertCacheEntry(model.getId(), model, DealerShipModel.class);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            log.error("Error getting dealerShip by Id {} - {}", dealerId, e.getMessage());
            throw new DealershipServiceException(e);
        }

        log.debug("done getting dealership {}", model.getName());
        return model;
    }

    @Override
    public List<DealerShipModel> getDealerShips(String clientId) throws DealershipServiceException {

        log.debug("Getting list of all dealerships for Client Id {}", clientId);
        String cacheKey = String.format("client-dealerShips-%s",clientId);

        List<DealerShipModel> dealerShipList = new ArrayList<>();
        DealerShipModel[] dealerShipModels;
        try {
            dealerShipModels = cacheService.getCacheEntry(cacheKey,DealerShipModel[].class);

            if(dealerShipModels == null){
                List<DealerShip> dealerShips = dealerShipDao.getDealerships(UUID.fromString(clientId));
                List<DealerShipModel> finalDealerShipList = dealerShipList;
                dealerShips.forEach(dealerShip -> finalDealerShipList.add(dealerShip.toModel()));

                dealerShipModels = new DealerShipModel[dealerShipList.size()];
                dealerShipModels = dealerShipList.toArray(dealerShipModels);
                cacheService.insertCacheEntry(cacheKey,dealerShipModels,DealerShipModel[].class);
            }else{
                dealerShipList = Arrays.asList(dealerShipModels);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            log.error("Error getting list of dealerships for ClientId {}",clientId);
            throw new DealershipServiceException(e);
        }


        return dealerShipList;
    }
}
