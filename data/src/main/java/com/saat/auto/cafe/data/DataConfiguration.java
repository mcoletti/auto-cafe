package com.saat.auto.cafe.data;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastConfigResourceCondition;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastInstanceFactory;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by micahcoletti on 8/2/16.
 */
@Configuration
@EnableAutoConfiguration(exclude = {
        ErrorMvcAutoConfiguration.class,
        HazelcastAutoConfiguration.class,
        HazelcastConfigResourceCondition.class,
        HazelcastInstanceFactory.class
})
@ComponentScan(value = {"com.saat.auto.cafe.data.*"})
// @PropertySource("classpath:application.yml")
@EnableConfigurationProperties(CassandraProperties.class)
public class DataConfiguration {

//    @Autowired
//    MigrateProperties dbProperties;


}
