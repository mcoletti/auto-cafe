package com.saat.auto.cafe.data.dao;

import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.data.dao.impl.VehicleDaoImpl;

/**
 * Created by mcoletti on 6/7/16.
 */
public class DaoFactory {


    /**
     * Factory Method to get the default instance of the Vehicle DAO
     * @return a new instace of the Vehicle Dao
     */
    public static VehicleDao getVehicleDao(CassandraInstance cassandraInstance){

        return new VehicleDaoImpl(cassandraInstance);

    }

}
