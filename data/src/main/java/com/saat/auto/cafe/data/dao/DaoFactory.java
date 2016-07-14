package com.saat.auto.cafe.data.dao;

import com.saat.auto.cafe.common.interfaces.CatalogImagesDao;

import javax.sql.DataSource;

/**
 * Created by mcoletti on 6/7/16.
 */
public class DaoFactory {



    /**
     * Factory Method to get an instance of the CatalogDao
     * @param dataSource
     * @return
     */
    public static CatalogImagesDao getCatalogImagesDao(DataSource dataSource){
        return new CatalogImagesDaoOldImpl(dataSource);
    }
}
