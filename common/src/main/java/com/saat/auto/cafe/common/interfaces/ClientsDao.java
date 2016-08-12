package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.entitys.ClientVehicle;

import java.util.List;
import java.util.UUID;

/**
 * Created by mcoletti on 6/16/16.
 */
public interface ClientsDao {

    Client upsert(Client client) throws DaoException;
    Client get(UUID id) throws DaoException;
}
