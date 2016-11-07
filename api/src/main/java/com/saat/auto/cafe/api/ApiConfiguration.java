package com.saat.auto.cafe.api;

import com.saat.auto.cafe.data.DataConfiguration;
import com.saat.auto.cafe.service.ServiceConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by mcoletti on 6/1/16.
 */
@Configuration
@ComponentScan(value = {"com.saat.auto.cafe.api.*"})
@PropertySource("classpath:application.yml")
@Import({
        DataConfiguration.class,
        ServiceConfiguration.class
})
public class ApiConfiguration {

}
