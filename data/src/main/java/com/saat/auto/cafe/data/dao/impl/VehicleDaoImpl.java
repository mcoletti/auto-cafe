package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.saat.auto.cafe.common.AutoCafeConstants.Schema.VehicleImagesTbl;
import com.saat.auto.cafe.common.entitys.Vehicle;
import com.saat.auto.cafe.common.entitys.VehicleCollection;
import com.saat.auto.cafe.common.entitys.VehicleImage;
import com.saat.auto.cafe.common.exceptions.ClientVehicleException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.data.dao.accessors.VehicleAccessor;
import com.saat.auto.cafe.common.interfaces.daos.VehicleDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

/**
 * Created by micahcoletti on 7/18/16.
 */
@Component
public class VehicleDaoImpl implements VehicleDao {


    private static final Logger log = LoggerFactory.getLogger(DealerShipDaoImpl.class);

    private final CassandraInstance ci;
    private Mapper<Vehicle> vehicleMapper;
    private VehicleAccessor vehicleAccessor;

    /**
     * Constructor used for testing
     *
     * @param ci the cassandra instance
     */
    @Autowired
    public VehicleDaoImpl(CassandraInstance ci) {
        this.ci = ci;
        vehicleAccessor = new MappingManager(ci.getSession()).createAccessor(VehicleAccessor.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleImage insertVehicleImage(VehicleImage vi) throws ClientVehicleException {

        try {
            Mapper<VehicleImage> viMapper = ci.mappingManager().mapper(VehicleImage.class);
            viMapper.save(vi);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientVehicleException(e);
        }
        return vi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VehicleImage getVehicleImage(UUID dealershipId,String stockNum) throws ClientVehicleException {

       VehicleImage vi;
        try {
            Result<VehicleImage> imageResult = vehicleAccessor.qryForImageList(dealershipId,stockNum);
            vi = imageResult.one();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error Getting the image List for dealershipId: {} - {}", dealershipId, e.getMessage());
            throw new ClientVehicleException(e);
        }
        return vi;
    }

    @Override
    public Vehicle upsert(Vehicle cv) throws ClientVehicleException {


        try {
            vehicleMapper = ci.mappingManager().mapper(Vehicle.class);
            vehicleMapper.save(cv);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClientVehicleException(e);
        }


        return cv;
    }

    @Override
    public VehicleCollection get(UUID dealerId) throws ClientVehicleException {

        List<Vehicle> vehicleList = null;

        try {

            Result<Vehicle> vehicles = vehicleAccessor.qryByDealerShipId(dealerId);
            vehicleList = vehicles.all();
        } catch (Exception e) {
            e.printStackTrace();
        }
        VehicleCollection cvc = VehicleCollection.builder()
                .vehicles(vehicleList).build();

        return cvc;
    }

    @Override
    public Vehicle get(UUID dealerId, String stockNum) throws ClientVehicleException {

        Vehicle cv;

        try {
            Result<Vehicle> clientVehicles = vehicleAccessor.qryByDealerShipIdAndVehicleId(dealerId, stockNum);
            cv = clientVehicles.one();
        } catch (Exception e) {
            if (e.getMessage().contains("0 rows")) {
                cv = null;
            } else {
                e.printStackTrace();
                log.error("Error getting the DealerShip Vehicle for dealershipId: {} and StockNum: {} - {}", dealerId, stockNum, e.getMessage());
                throw new ClientVehicleException(e);
            }
        }


        return cv;
    }
}
