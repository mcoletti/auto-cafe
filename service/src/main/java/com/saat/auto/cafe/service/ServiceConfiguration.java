package com.saat.auto.cafe.service;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastConfigResourceCondition;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastInstanceFactory;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by micahcoletti on 8/3/16.
 */
@Configuration
@EnableAutoConfiguration(exclude = {
        ErrorMvcAutoConfiguration.class,
        CassandraDataAutoConfiguration.class,
        HazelcastAutoConfiguration.class,
        HazelcastConfigResourceCondition.class,
        HazelcastInstanceFactory.class
})
//@ComponentScan(value = {"com.saat.auto.cafe.service.*","com.saat.auto.cafe.data.*"})
@ComponentScan(value = {"com.saat.auto.cafe.service.*"})
@EnableConfigurationProperties(HazelCastProperties.class)
public class ServiceConfiguration {
}
