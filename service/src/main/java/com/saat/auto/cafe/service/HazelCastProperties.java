package com.saat.auto.cafe.service;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by micahcoletti on 8/22/16.
 */
@ConfigurationProperties(locations = "classpath:application.yml",prefix = "hazelcast")
@Component
@Data
public class HazelCastProperties {
//    @Value("${maxIdleSecs}")
    private int maxIdleSecs;
//    @Value("${ttl}")
    private int ttl;
//    @Value("${cacheName}")
    private String cacheName;

}
