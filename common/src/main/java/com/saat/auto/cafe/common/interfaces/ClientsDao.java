package com.saat.auto.cafe.common.interfaces;

import com.saat.auto.cafe.common.models.ClientLocations;
import com.saat.auto.cafe.common.models.ClientVehicles;
import com.saat.auto.cafe.common.models.Clients;

import java.util.List;
import java.util.UUID;

/**
 * Created by mcoletti on 6/16/16.
 */
public interface ClientsDao {

    Clients upsert(Clients clients);
    Clients get(UUID id);
    Clients get(String clientName);
    List<ClientLocations> getClientLocations(UUID clientId);
    List<ClientVehicles> getClientVehicles(UUID clientId);
}
