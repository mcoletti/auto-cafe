package com.saat.auto.cafe.common.interfaces.daos;

import com.saat.auto.cafe.common.entitys.DealerShip;
import com.saat.auto.cafe.common.exceptions.DaoException;

import java.util.UUID;

/**
 * Created by mcoletti on 6/16/16.
 */
public interface DealerShipDao {

    DealerShip upsert(DealerShip dealerShip) throws DaoException;
    DealerShip get(UUID id) throws DaoException;
}
