package com.saat.auto.cafe.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by micahcoletti on 7/28/16.
 */
@Component
@Data
public class CassandraProps {

    @Value("${contactPoints}")
    private String contactPoints;
    @Value("${keySpace}")
    private String keySpaceName;
    @Value("${port}")
    private String port;
    @Value("${userName}")
    private String userName;
    @Value("${password}")
    private String password;


}
