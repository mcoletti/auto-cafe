package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.mapping.Mapper;
import com.saat.auto.cafe.common.db.SchemaConstants;
import com.saat.auto.cafe.common.db.SchemaConstants.ClientsTable;
import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.entitys.ClientVehicle;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.ClientsDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by mcoletti on 6/16/16.
 */
@Component
public class ClientsDaoImpl implements ClientsDao {


    private static final Logger log = LoggerFactory.getLogger(ClientsDaoImpl.class);

    private final CassandraInstance ci;
    private Mapper<Client> clientMapper;

    @Autowired
    public ClientsDaoImpl(CassandraInstance ci) {
        this.ci = ci;
    }

    @Override
    public Client upsert(Client client) throws DaoException {

        try {
            log.debug("Inserting/Updating new ClientsModel - ClientsModel Name: {} and id: {}", client.getClientName(), client.getId());
            clientMapper = ci.mappingManager().mapper(Client.class);
            clientMapper.save(client);
        } catch (Exception e) {
            log.error("Error updating or inserting ClientsModel Record - {}", e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }

        return client;
    }

    @Override
    public Client get(UUID id) throws DaoException {


        Client client;
        try {
            log.debug("Getting ClientsModel Record for id: {}", id);
            clientMapper = ci.mappingManager().mapper(Client.class);
            client = clientMapper.get(id);
        } catch (Exception e) {
            log.error("Error updating or inserting ClientsModel Record - {}", e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }
        return client;
    }
}
