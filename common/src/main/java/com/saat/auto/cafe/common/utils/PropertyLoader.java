package com.saat.auto.cafe.common.utils;


import org.apache.commons.configuration.Configuration;

import java.net.URL;

/**
 * Created by mcoletti on 2/17/16.
 */
public interface PropertyLoader {
    String getProperty(String resourceKey);

    String getProperty(String resourceKey, URL resource);

    String getPropertyByPreFix(String resourceKey, String preFix);

    /**
     * Retunrs a List of Configuration Objects
     *
     * @param preFix
     * @return
     * @throws Exception
     */
    Configuration getConfigByPreFIx(String preFix);

    /**
     * Geet the Root Base Configuration
     * @return
     */
    Configuration getConfig();


    Configuration getConfigByPreFIx(String preFix, URL resource);

    URL getDefaultResourceUrl();
}
