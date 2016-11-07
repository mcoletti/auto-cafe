package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.daos.ClientDao;
import com.saat.auto.cafe.data.dao.accessors.ClientAccessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by micahcoletti on 10/2/16.
 */
@Component
public class ClientDaoImpl implements ClientDao {

    private static final Logger log = LoggerFactory.getLogger(ClientDaoImpl.class);

    private final CassandraInstance ci;
    private final ClientAccessor clientAccessor;

    @Autowired
    public ClientDaoImpl(CassandraInstance ci) {

        this.ci = ci;
        clientAccessor = new MappingManager(ci.getSession()).createAccessor(ClientAccessor.class);
    }

    @Override
    public Client upsert(Client client) throws DaoException {


        try {
            Mapper<Client> clientMapper = ci.mappingManager().mapper(Client.class);
            clientMapper.save(client);
        } catch (Exception e) {
            log.error("Error Adding or Updating the Client data for ClientId {} - {}", client.getId(), e.getMessage());
            throw new DaoException(e);
        }
        return client;
    }

    @Override
    public Client get(UUID id) throws DaoException {


        Client client = null;
        try {
            Result<Client> clientResult = clientAccessor.qryById(id);
            client = clientResult.one();
        } catch (Exception e) {
            log.error("Error Getting Client for Client Id {} - {}", id, e.getMessage());
            throw new DaoException(e);
        }


        return client;
    }

    @Override
    public Client get(String name) {
        Client client = null;
        try {
            Result<Client> clientResult = clientAccessor.qryByName(name);
            client = clientResult.one();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error Getting Client for Client {} - {}", name, e.getMessage());
        }


        return client;
    }

    @Override
    public List<Client> getClientList() throws DaoException {

        List<Client> clientList;
        try {
            Result<Client> clientResult = clientAccessor.qryForAll();
            clientList = clientResult.all();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error Getting Client List {}", e.getMessage());
            throw new DaoException(e);
        }
        return clientList;
    }
}
