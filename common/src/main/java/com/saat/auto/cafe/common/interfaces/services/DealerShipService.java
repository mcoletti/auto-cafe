package com.saat.auto.cafe.common.interfaces.services;

import com.saat.auto.cafe.common.exceptions.DealershipServiceException;
import com.saat.auto.cafe.common.models.DealerShipModel;

import java.util.List;

/**
 * Created by micahcoletti on 9/1/16.
 */
public interface DealerShipService {

    /**
     * Add or Update a dealership entry.
     * @param dealerShipModel
     * @return an instance of the dealership model
     */
    void upsertDealserShip(DealerShipModel dealerShipModel) throws DealershipServiceException;

    /**
     * Ge a dealership instance based off dealerId
     * @param dealerId the dealership id
     * @return an instance of the dealership
     */
    DealerShipModel get(String dealerId) throws DealershipServiceException;

    /**
     * Get all dealerships based off client
     * @param clientId the client id to filter for
     * @return an array of dealership objects
     */
    List<DealerShipModel> getDealerShips(String clientId) throws DealershipServiceException;


}
