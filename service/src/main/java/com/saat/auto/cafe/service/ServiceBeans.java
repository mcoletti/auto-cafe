package com.saat.auto.cafe.service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.saat.auto.cafe.common.interfaces.CacheService;
import com.saat.auto.cafe.common.interfaces.HazelCastService;
import com.saat.auto.cafe.service.cache.HazelCastCacheServiceImpl;

import org.apache.commons.configuration.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * Created by micahcoletti on 7/22/16.
 */
@org.springframework.context.annotation.Configuration
public class ServiceBeans {

    /**
     * Bean for initializing a local hazelcast instance
     * @return
     */
    @Bean
    public HazelcastInstance getHazelcastInstance(){
        return Hazelcast.newHazelcastInstance();
    }

    /**
     * Bean for initializing the HazelCast Service
     * @return
     */
    @Bean
    public HazelCastService getHazelCastService(){
        return new HazelCastServiceImpl(getHazelcastInstance());
    }


}
