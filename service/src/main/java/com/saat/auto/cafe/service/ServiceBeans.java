package com.saat.auto.cafe.service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import org.apache.commons.configuration.Configuration;
import org.springframework.context.annotation.Bean;

/**
 * Created by micahcoletti on 7/22/16.
 */
@org.springframework.context.annotation.Configuration
public class ServiceBeans {


    @Bean
    public HazelcastInstance getHazelcastInstance(){

        return Hazelcast.newHazelcastInstance();


    }


}
