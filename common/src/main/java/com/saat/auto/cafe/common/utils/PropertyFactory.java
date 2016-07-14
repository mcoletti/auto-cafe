package com.saat.auto.cafe.common.utils;

import org.apache.commons.configuration.Configuration;

import java.io.File;
import java.net.URL;

/**
 * Created by mcoletti on 6/1/16.
 */
public class PropertyFactory {
    public static Configuration getConfig() throws Exception {

        // Default to the User Run directory then check for the environment var
        String propertyFilePath = System.getProperty("user.dir") + "/app.properties";
        return getConfig(propertyFilePath);
    }

    public static Configuration getConfig(String path) throws Exception {

        // Default to the User Run directory then check for the environment var
        String propertyFilePath = path;
        if(System.getProperty("prop.path") != null){
            propertyFilePath = System.getProperty("prop.path");
        }
        File file = new File(propertyFilePath);
        URL url = file.toURI().toURL();
        PropertyLoaderImpl propertyLoader = new PropertyLoaderImpl(url);
        Configuration config = propertyLoader.getConfig();
        return config;
    }
}
