package com.saat.auto.cafe.service;

import com.google.gson.Gson;

import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.HazelcastInstance;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.HazelCastService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by micahcoletti on 7/22/16.
 */

public class HazelCastCacheServiceImpl implements CacheService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private HazelCastService hazelCastService;
    private Gson gson;
    private String CACHE_MAP;


    /**
     * Full Constructor
     */
    public HazelCastCacheServiceImpl(HazelCastService hazelCastService, String cacheMap, Gson gson) {

        log.debug("Initializing the Cache Service for CacheMap: {}", cacheMap);
        this.hazelCastService = hazelCastService;
        this.gson = gson;
        this.CACHE_MAP = cacheMap;



    }

    @Override
    public <T> T getCacheEntry(String key, Type type) {
        log.debug("Getting cache entry for key: {} and Map: {}", key, CACHE_MAP);
        ConcurrentMap<String, String> map = hazelCastService.getMap(CACHE_MAP);
        T value = gson.fromJson(map.get(key), type);
        return value;
    }

    @Override
    public String toJson(Object entry, Type type) {

        return gson.toJson(entry, type);
    }

    @Override
    public void insertCacheEntry(String key, String entry) {

        log.debug("Inserting cache entry for key: {} and Map: {}", key, CACHE_MAP);
        ConcurrentMap<String, String> map = hazelCastService.getMap(CACHE_MAP);
        map.put(key, entry);
    }
}
