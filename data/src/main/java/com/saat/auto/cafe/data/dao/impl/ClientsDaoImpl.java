package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.saat.auto.cafe.common.db.SchemaConstants;
import com.saat.auto.cafe.common.db.SchemaConstants.ClientsTable;
import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.entitys.ClientVehicle;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.ClientsDao;
import com.saat.auto.cafe.data.dao.mappers.ClientRowMapper;

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

    @Autowired
    public ClientsDaoImpl(CassandraInstance ci) {
        this.ci = ci;
    }

    @Override
    public Client upsert(Client client) throws DaoException {

        // Check to see if customer is already in DB
        Client clUpdate = get(client.getId());
        try {
            if (clUpdate == null) {
                log.debug("Inserting new ClientsModel - ClientsModel Name: {} and id: {}", client.getClientName(), client.getId());

                ci.getCassandraTemplate().execute(client.getInsertStatement(ci.getCluster()));

//                ci.getCassandraTemplate().insert(client);
            } else {
                log.debug("Updating ClientsModel - ClientsModel Name: {} and id: {}", client.getClientName(), client.getId());

                ci.getCassandraTemplate().execute(client.getUpdateStatement(ci.getCluster()));

//                ci.getCassandraTemplate().update(client);
            }
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
            Select s = QueryBuilder.select().from("clients");
            s.where(QueryBuilder.eq(SchemaConstants.Common.Columns.Id, id));
            client = ci.getCassandraTemplate().queryForObject(s, new ClientRowMapper()); // .queryForObject(s, new ClientRowMapper());
//            client = clients.size() != 0 ? clients.get(0) : null;
        } catch (Exception e) {
            log.error("Error updating or inserting ClientsModel Record - {}", e.getMessage());
            if (e.getMessage().contains("0 rows")) {
                client = null;
            } else {
                e.printStackTrace();
                throw new DaoException(e);
            }
        }
        return client;
    }

    @Override
    public Client get(String clientName) throws DaoException {

        Client client;
        try {
            Select s = QueryBuilder.select().from(ClientsTable.NAME);
            s.where(QueryBuilder.eq(ClientsTable.Columns.ClientName, clientName));
            client = ci.getCassandraTemplate().queryForObject(s, new ClientRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return client;
    }





    @Override
    public List<ClientVehicle> getClientVehicles(UUID clientId) {
        return null;
    }
}
