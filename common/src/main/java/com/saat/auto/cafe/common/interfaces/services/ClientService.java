package com.saat.auto.cafe.common.interfaces.services;

import com.saat.auto.cafe.common.exceptions.ClientServiceException;
import com.saat.auto.cafe.common.models.ClientModel;

import java.util.List;

/**
 * Created by micahcoletti on 11/7/16.
 */
public interface ClientService {


    void upsertClient(ClientModel clientModel) throws ClientServiceException;

    ClientModel getClient(String uuid, boolean resetCache) throws ClientServiceException;

    List<ClientModel> getClientList() throws ClientServiceException;
    List<ClientModel> getClientList(boolean resetCache) throws ClientServiceException;


}
