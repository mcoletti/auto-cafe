package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.models.ClientVehicles;
import com.saat.auto.cafe.common.models.Clients;

import java.util.List;
import java.util.UUID;

/**
 * Created by mcoletti on 6/16/16.
 */
public interface ClientsDao {

    Clients upsert(Clients clients) throws DaoException;
    Clients get(UUID id) throws DaoException;
    Clients get(String clientName) throws DaoException;
    List<ClientVehicles> getClientVehicles(UUID clientId);
}
