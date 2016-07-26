package com.saat.auto.cafe.common.interfaces;

import java.lang.reflect.Type;

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
     * Method to insert a cached entry into cache
     * @param key the key of the entry to insert
     * @param entry a instance instance of the entry as a JSON String
     */
    void insertCacheEntry(String key, String entry);

    /**
     * Helper method to convert a Object to a JSON String
     * @param entry
     * @param type
     * @return
     */
    String toJson(Object entry,Type type);
}





