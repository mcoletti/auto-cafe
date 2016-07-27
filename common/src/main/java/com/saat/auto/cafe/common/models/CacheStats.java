package com.saat.auto.cafe.common.models;

import lombok.Builder;

/**
 * Created by micahcoletti on 7/27/16.
 */
@Builder
public class CacheStats {

    private String cacheName;
    private long heapUsedInKb;
    private long totalCacheEntries;
    private long totalBackUpCacheEntries;
    private String lastAccessDtm;
    private String lastUpdateDtm;

}
