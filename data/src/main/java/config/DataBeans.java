package config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by micahcoletti on 8/2/16.
 */
@Configuration
@EnableAutoConfiguration(exclude = {
    ErrorMvcAutoConfiguration.class,
        CassandraDataAutoConfiguration.class})
@ComponentScan(value = {"com.saat.auto.cafe.data.*"})
@PropertySource("classpath:application.yml")
public class DataBeans {

}
