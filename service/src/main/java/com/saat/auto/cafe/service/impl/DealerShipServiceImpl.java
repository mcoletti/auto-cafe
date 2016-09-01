package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.DealerShipDao;
import com.saat.auto.cafe.common.interfaces.DealerShipService;
import com.saat.auto.cafe.common.models.DealerShipModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by micahcoletti on 9/1/16.
 */
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
    public DealerShipModel upsertDealserShip(DealerShipModel dealerShipModel) {
        return null;
    }

    @Override
    public DealerShipModel get(String dealerId) {
        return null;
    }
}
