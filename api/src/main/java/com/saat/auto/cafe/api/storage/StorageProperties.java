package com.saat.auto.cafe.api.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "storage")
@Data
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location;

}
