package com.saat.auto.cafe.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by micahcoletti on 7/28/16.
 */
// @Component
// @ConfigurationProperties(locations = "classpath:application.yml")
@Data
public class MigrateProperties {

//    @Value("${contactPoints}")
    private String contactPoints;
//    @Value("${keySpace}")
    private String keySpace;
//    @Value("${port}")
    private int port;
//    @Value("${userName}")
    private String userName;
//    @Value("${password}")
    private String password;
//    @Value("${consistencyLevel}")
    private String consistencyLevel;


//    public String getContactPoints() {
//        return contactPoints;
//    }
//
//    public void setContactPoints(String contactPoints) {
//        this.contactPoints = contactPoints;
//    }
//
//    public String getKeySpace() {
//        return keySpace;
//    }
//
//    public void setKeySpace(String keySpace) {
//        this.keySpace = keySpace;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getConsistencyLevel() {
//        return consistencyLevel;
//    }
//
//    public void setConsistencyLevel(String consistencyLevel) {
//        this.consistencyLevel = consistencyLevel;
//    }
}
