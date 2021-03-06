package com.saat.auto.cafe.service.cache;

import com.google.gson.Gson;

import com.hazelcast.core.Member;
import com.hazelcast.monitor.LocalMapStats;
import com.saat.auto.cafe.common.interfaces.services.CacheService;
import com.saat.auto.cafe.common.interfaces.services.HazelCastService;
import com.saat.auto.cafe.common.models.CacheNode;
import com.saat.auto.cafe.common.models.CacheStats;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
     * Default Constructor
     */
    @Autowired
    protected HazelCastCacheServiceImpl(HazelCastService hazelCastService) {
        this.hazelCastService = hazelCastService;
        this.gson = new Gson();
    }

    public void setCACHE_MAP(String CACHE_MAP) {
        this.CACHE_MAP = CACHE_MAP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getCacheEntry(String key, Type type) {
        log.debug("Getting cache entry for key: {} and Map: {}", key, CACHE_MAP);
        ConcurrentMap<String, String> map = hazelCastService.getMap(CACHE_MAP);
        T value = map.get(key) != null ? gson.fromJson(map.get(key), type) : null;
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertCacheEntry(String key, Object entry, Type type) {

        log.debug("Inserting cache entry for key: {} and Map: {}", key, CACHE_MAP);

        // First check to see if entry is already in cache
        if (isInCache(key)) {
            // Remove it if found
            removeEntry(key);
        }

        ConcurrentMap<String, String> map = hazelCastService.getMap(CACHE_MAP);

        String json = getGson().toJson(entry, type);
        map.put(key, json);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Gson getGson() {
        return gson;
    }

    @Override
    public boolean isInCache(String key) {

        return hazelCastService.getMap(CACHE_MAP).containsKey(key);
    }

    @Override
    public void removeEntry(String key) {

        hazelCastService.getMap(CACHE_MAP).remove(key);

    }

    @Override
    public CacheStats getLocalCacheStats(String cacheName) {

        LocalMapStats mapStats = hazelCastService.getLocalMapStats(CACHE_MAP);

        CacheStats cacheStats = CacheStats.builder()
                .heapUsedInKb(mapStats.getHeapCost())
                .totalCacheEntries(mapStats.getOwnedEntryCount())
                .totalBackUpCacheEntries(mapStats.getBackupEntryCount())
                .lastAccessDtm(new DateTime(mapStats.getLastAccessTime()).toString())
                .lastUpdateDtm(new DateTime(mapStats.getLastUpdateTime()).toString()).build();
        return cacheStats;
    }

    @Override
    public List<CacheNode> getCacheNodes() {

        Set<Member> members = hazelCastService.getHazelcastMembers();

        List<CacheNode> cacheNodes = new ArrayList<>();
        members.forEach(member -> {

            CacheNode cacheNode = CacheNode.builder()
                    .uuid(member.getUuid())
                    .ipAddress(member.getSocketAddress().getAddress().getHostAddress())
                    .hostName(member.getSocketAddress().getHostName()).build();

            cacheNodes.add(cacheNode);
        });
        return cacheNodes;
    }
}
