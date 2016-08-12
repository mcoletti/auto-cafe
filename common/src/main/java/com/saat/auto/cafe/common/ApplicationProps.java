package com.saat.auto.cafe.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by micahcoletti on 7/28/16.
 */
@Component
@Data
public class ApplicationProps {

    @Value("${contactPoints}")
    private String contactPoints;
    @Value("${keySpace}")
    private String keySpace;
    @Value("${port}")
    private int port;
    @Value("${userName}")
    private String userName;
    @Value("${password}")
    private String password;
    @Value("${consistencyLevel}")
    private String consistencyLevel;
    @Value("${maxIdleSecs}")
    private int maxIdleSecs;
    @Value("${ttl}")
    private int ttl;
    @Value("${cacheName}")
    private String cacheName;


}
