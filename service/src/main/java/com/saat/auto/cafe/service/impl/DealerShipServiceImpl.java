package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.exceptions.DealershipServiceException;
import com.saat.auto.cafe.common.interfaces.services.CacheService;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.common.interfaces.services.DealerShipService;
import com.saat.auto.cafe.common.models.DealerShipModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by micahcoletti on 9/1/16.
 */
@Service
public class DealerShipServiceImpl implements DealerShipService {


    private static final Logger log = LoggerFactory.getLogger(DealerShipServiceImpl.class);

    private final CacheService cacheService;
    private final DealerShipDao dealerShipDao;

    @Autowired
    public DealerShipServiceImpl(CacheService cacheService, DealerShipDao dealerShipDao) {
        this.cacheService = cacheService;
        this.dealerShipDao = dealerShipDao;
    }

    @Override
    public DealerShipModel upsertDealserShip(DealerShipModel dealerShipModel) throws DealershipServiceException {

        DealerShipModel model;
        UUID dealershipId = dealerShipModel.getId() == null ? UUID.randomUUID() : UUID.fromString(dealerShipModel.getId());
        try {
            dealerShipDao.upsert(dealerShipModel.toEntity());

            String cacheKey = String.format("%s",dealershipId.toString());

            model = cacheService.getCacheEntry(cacheKey,DealerShipModel.class);

            if(model != null){
                cacheService.removeEntry(cacheKey);
            }

            DealerShip dealerShip = dealerShipDao.get(dealershipId);
            model = dealerShip.toModel();
            cacheService.insertCacheEntry(cacheKey,model,DealerShipModel.class);

        } catch (DaoException e) {
            e.printStackTrace();
            log.debug("Error adding or updating the dealership entity for Dealership {} - {}",dealerShipModel.getName(),e.getMessage());
            throw new DealershipServiceException(e);
        }


        return model;
    }

    @Override
    public DealerShipModel get(String dealerId) {

        DealerShipModel model = null;
        try {

            model = cacheService.getCacheEntry(dealerId,DealerShipModel.class);
            if(model == null){
                DealerShip dealerShip = dealerShipDao.get(UUID.fromString(dealerId));
                model = dealerShip.toModel();
                cacheService.insertCacheEntry(dealerId,model,DealerShipModel.class);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            log.error("Error getting dealerShip by Id {} - {}",dealerId,e.getMessage());
        }

        return model;
    }

    @Override
    public List<DealerShipModel> getDealerShips(String clientId) {
        return null;
    }
}
