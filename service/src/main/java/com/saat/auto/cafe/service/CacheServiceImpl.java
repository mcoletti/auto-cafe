package com.saat.auto.cafe.service;

import com.hazelcast.core.HazelcastInstance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by micahcoletti on 7/22/16.
 */

public class CacheServiceImpl {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private HazelcastInstance hazelcastInstance;



}
