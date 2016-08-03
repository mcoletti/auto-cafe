package com.saat.auto.cafe.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by micahcoletti on 8/3/16.
 */
@Component
@Data
public class HazelCastConfig {

    @Value("${maxIdleSecs}")
    private int maxIdleSecs;
    @Value("${ttl}")
    private int ttl;
    @Value("${cacheName}")
    private String cacheName;


}
