package com.saat.auto.cafe.data.dao;

import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.daos.ClientDao;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;
import com.saat.auto.cafe.data.dao.impl.ClientDaoImpl;
import com.saat.auto.cafe.data.dao.impl.DealerShipDaoImpl;
import com.saat.auto.cafe.data.dao.impl.VehicleDaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by mcoletti on 6/7/16.
 */
@Component
public class DaoFactory {


    @Autowired
    private CassandraInstance cassandraInstance;


    /**
     * Factory Method to get the default instance of the Vehicle DAO
     * @return a new instace of the Vehicle Dao
     */

    public VehicleDao getVehicleDao(){

        return new VehicleDaoImpl(cassandraInstance);

    }

    public ClientDao getClientDao(){

        return new ClientDaoImpl(cassandraInstance);

    }
    public DealerShipDao getDealerShipDao(){

        return new DealerShipDaoImpl(cassandraInstance);

    }

}
