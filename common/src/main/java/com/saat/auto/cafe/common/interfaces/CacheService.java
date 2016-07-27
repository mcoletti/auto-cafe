package com.saat.auto.cafe.common.interfaces;

import com.google.gson.Gson;

import com.saat.auto.cafe.common.models.CacheNode;
import com.saat.auto.cafe.common.models.CacheStats;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by micahcoletti on 7/26/16.
 */
public interface CacheService {

    /**
     * Method to get a Cached Entry from Cache
     * @param key the entry key
     * @param type the Type of entry to get
     * @param <T> the generic type which converts it to the proper type
     * @return an instance of a converted generic type object
     */
    <T> T getCacheEntry(String key, Type type);

    /**
     * Method to insert a generic type cached entry into cache
     * @param key the key of the entry to insert
     * @param entry a instance instance of the entry as a JSON String
     * @param type the type of the entry to searialse
     */
    void insertCacheEntry(String key, Object entry,Type type);

    /**
     * Method to insert a non-generic type entry into cache
     * @param key the key to use when added to the cache map
     * @param entry the entry object to add
     */
    void insertCacheEntry(String key, Object entry);

    /**
     * Method to Get the injected Gson object
     * @return
     */
    Gson getGson();

    /**
     * Default method to convert a Object to a JSON String
     * @param entry
     * @param type
     * @return
     */
    default String toJson(Object entry,Type type){
      return getGson().toJson(entry, type);
    }

    /**
     * Method to check if Model is if Cache
     * @param key the key to check for
     * @return true if found in cache
     */
    boolean isInCache(String key);

    /**
     * Method to remove a cached entry from cache
     * @param key
     */
    void removeEntry(String key);

    CacheStats getLocalCacheStats(String cacheName);

    List<CacheNode> getCacheNodes();

}





