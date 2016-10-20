package com.saat.auto.cafe.data;

import com.saat.auto.cafe.data.dao.CassandraInstanceImpl;
import com.saat.auto.cafe.data.dao.impl.VehicleDaoImpl;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastConfigResourceCondition;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastInstanceFactory;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by micahcoletti on 8/2/16.
 */
@Configuration
@EnableAutoConfiguration(exclude = {
        ErrorMvcAutoConfiguration.class,
        CassandraDataAutoConfiguration.class,
        HazelcastAutoConfiguration.class,
        HazelcastConfigResourceCondition.class,
        HazelcastInstanceFactory.class
})
@ComponentScan(value = {"com.saat.auto.cafe.data.*"})
@EnableConfigurationProperties(DbProperties.class)
public class DataConfiguration {

//    @Autowired
//    DbProperties dbProperties;


}
