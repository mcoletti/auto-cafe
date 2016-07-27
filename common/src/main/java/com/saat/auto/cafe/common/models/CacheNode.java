package com.saat.auto.cafe.common.models;

import lombok.Builder;

/**
 * Created by micahcoletti on 7/27/16.
 */
@Builder
public class CacheNode {

    private String uuid;
    private String hostName;
    private String ipAddress;
}
