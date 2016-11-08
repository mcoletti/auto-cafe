package com.saat.auto.cafe.service;

import lombok.Data;

/**
 * Created by micahcoletti on 11/8/16.
 */
@Data
public class CacheProperties {

    private String name;
    private int maxIdleSecs;
    private int ttl;
}
