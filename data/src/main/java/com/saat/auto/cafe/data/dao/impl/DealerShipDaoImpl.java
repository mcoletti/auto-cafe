package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.entitys.DealershipLot;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.daos.DealerShipDao;
import com.saat.auto.cafe.data.dao.accessors.DealershipAccessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by mcoletti on 6/16/16.
 */
@Component
public class DealerShipDaoImpl implements DealerShipDao {


    private static final Logger log = LoggerFactory.getLogger(DealerShipDaoImpl.class);

    private final CassandraInstance ci;
    private Mapper<DealerShip> clientMapper;
    private Mapper<DealershipLot> dealershipLotMapper;
    private final DealershipAccessor dealershipAccessor;

    @Autowired
    public DealerShipDaoImpl(CassandraInstance ci) {

        this.ci = ci;
        dealershipAccessor = new MappingManager(ci.getSession()).createAccessor(DealershipAccessor.class);
    }

    @Override
    public DealerShip upsert(DealerShip dealerShip) throws DaoException {

        try {
            log.debug("Inserting/Updating new DealerShipModel - DealerShipModel Name: {} and id: {}", dealerShip.getName(), dealerShip.getId());
            clientMapper = ci.mappingManager().mapper(DealerShip.class);
            // Save/Update the dealership record in the db
            clientMapper.save(dealerShip);
        } catch (Exception e) {
            log.error("Error updating or inserting DealerShipModel Record - {}", e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }

        return dealerShip;
    }

    @Override
    public DealerShip get(UUID id) throws DaoException {


        DealerShip dealerShip;
        try {
            log.debug("Getting DealerShipModel Record for id: {}", id);
            Result<DealerShip> dealerShipResult = dealershipAccessor.qryById(id);
            dealerShip = dealerShipResult.one();
        } catch (Exception e) {
            log.error("Error getting Dealership Record for Id {} - {}", id, e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return dealerShip;
    }

    @Override
    public List<DealerShip> getDealerships(UUID clientId) throws DaoException {


        List<DealerShip> dealerShips;

        try {
            Result<DealerShip> dealerShipResult = dealershipAccessor.qryByClientId(clientId);
            dealerShips = dealerShipResult.all();
        } catch (Exception e) {
            log.error("Error getting Dealerships for Client Id {} - {}", clientId, e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);

        }


        return dealerShips;
    }

    @Override
    public void upsertDealerShipLot(DealershipLot dealershipLot) throws DaoException {

        try {
            dealershipLotMapper = ci.mappingManager().mapper(DealershipLot.class);
            dealershipLotMapper.save(dealershipLot);
        } catch (Exception e) {
            log.error("Error adding/updating new dealershipLot - {}", e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<DealershipLot> getDealershipLots(UUID dealerShipId) throws DaoException {

        List<DealershipLot> dealershipLots = null;
        try {
            Result<DealershipLot> dealershipLotResult = dealershipAccessor.qryForDealerShipLots(dealerShipId);
            dealershipLots = dealershipLotResult.all();
        } catch (Exception e) {
            log.error("Error getting list of lots for dealerShipId {} - {}", dealerShipId, e.getMessage());
            throw new DaoException(e);
        }


        return dealershipLots;
    }
}
