package com.saat.auto.cafe.service.impl;

import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.exceptions.ClientServiceException;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.interfaces.daos.ClientDao;
import com.saat.auto.cafe.common.interfaces.services.ClientService;
import com.saat.auto.cafe.common.models.ClientModel;
import com.saat.auto.cafe.service.cache.ClientCacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by micahcoletti on 11/7/16.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ClientDao clientDao;
    private final ClientCacheService clientCacheService;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao, ClientCacheService clientCacheService) {
        this.clientDao = clientDao;
        this.clientCacheService = clientCacheService;
    }


    @Override
    public void upsertClient(ClientModel clientModel) throws ClientServiceException {

        try {
            log.debug("Adding/Updating a Client in the backend system for Client {}", clientModel.getName());
            clientDao.upsert(clientModel.toEntity());

            // Get and insert the new Client into Cache
            clientModel = getClient(clientModel.getId());

            // Clear out the client list from cache
            String cacheKey = "client_list";
            clientCacheService.removeEntry(cacheKey);
        } catch (Exception e) {
            log.debug("Error Adding or Updating Clint {} - {}", clientModel.getName(), e.getMessage());
            throw new ClientServiceException(e);
        }
    }

    @Override
    public ClientModel getClient(String uuid) throws ClientServiceException {

        ClientModel clientModel = null;
        try {
            log.debug("Getting Client for Id {} from the backend", uuid);
            String cacheKey = String.format("%s", uuid);

            clientModel = clientCacheService.getCacheEntry(cacheKey, ClientModel.class);
            if (clientModel == null) {
                clientModel = clientDao.get(UUID.fromString(uuid)).toModel();
                clientCacheService.insertCacheEntry(cacheKey, clientModel, ClientModel.class);
            }
        } catch (DaoException e) {
            log.error("Add getting client for Id: {} - {}", uuid, e.getMessage());
            throw new ClientServiceException(e);
        }


        return clientModel;
    }

    @Override
    public List<ClientModel> getClientList() throws ClientServiceException {

        ClientModel[] clients = new ClientModel[0];
        try {
            String cacheKey = "client_list";

            clients = clientCacheService.getCacheEntry(cacheKey, ClientModel[].class);
            if (clients == null) {
                List<Client> clientList = clientDao.getClientList();

                List<ClientModel> clientModels = new ArrayList<>();
                clientList.forEach(client -> {
                    clientModels.add(client.toModel());
                });

                clients = new ClientModel[clientModels.size()];
                clients = clientModels.toArray(clients);

                clientCacheService.insertCacheEntry(cacheKey, clients, ClientModel[].class);
            }
        } catch (DaoException e) {
            log.error("Error getting the list off Clients  - {}", e.getMessage());
            throw new ClientServiceException(e);
        }


        return Arrays.asList(clients);
    }
}
