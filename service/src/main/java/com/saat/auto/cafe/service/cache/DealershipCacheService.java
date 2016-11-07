package com.saat.auto.cafe.service.cache;

import com.saat.auto.cafe.common.interfaces.services.HazelCastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by micahcoletti on 11/7/16.
 */
@Service
public class DealershipCacheService extends HazelCastCacheServiceImpl {
    /**
     * Default Constructor
     */

    private static final String CACHE_MAP = "dealerShip";

    @Autowired
    protected DealershipCacheService(HazelCastService hazelCastService) {
        super(hazelCastService);
        setCACHE_MAP(CACHE_MAP);
    }
}
