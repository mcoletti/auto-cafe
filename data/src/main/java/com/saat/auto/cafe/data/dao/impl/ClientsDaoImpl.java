package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.saat.auto.cafe.common.db.SchemaConstants;
import com.saat.auto.cafe.common.db.SchemaConstants.ClientsTable;
import com.saat.auto.cafe.common.exceptions.DaoException;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.ClientsDao;
import com.saat.auto.cafe.common.entitys.ClientVehicles;
import com.saat.auto.cafe.common.entitys.Clients;
import com.saat.auto.cafe.data.DataBeans;
import com.saat.auto.cafe.data.dao.mappers.ClientRowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by mcoletti on 6/16/16.
 */
@ContextConfiguration(classes = DataBeans.class)
public class ClientsDaoImpl implements ClientsDao {


    private static Logger log = LoggerFactory.getLogger(ClientsDaoImpl.class);

    @Inject
    private CassandraInstance ci;

    protected ClientsDaoImpl(CassandraInstance cassandraInstance) {
        this.ci = cassandraInstance;
    }


    @Override
    public Clients upsert(Clients clients) throws DaoException {

        // Check to see if customer is already in DB
        Clients clUpdate = get(clients.getId());
        try {
            if (clUpdate == null) {
                log.debug("Inserting new Client - Client Name: {} and id: {}", clients.getClientName(), clients.getId());

                ci.getCassandraOperations().execute(clients.getInsertStatement(ci.getCluster()));

//                ci.getCassandraOperations().insert(clients);
            } else {
                log.debug("Updating Client - Client Name: {} and id: {}", clients.getClientName(), clients.getId());

                ci.getCassandraOperations().execute(clients.getUpdateStatement());

//                ci.getCassandraOperations().update(clients);
            }
        } catch (Exception e) {
            log.error("Error updating or inserting Client Record - {}", e.getMessage());
            e.printStackTrace();
            throw new DaoException(e);
        }

        return clients;
    }

    @Override
    public Clients get(UUID id) throws DaoException {


        List<Clients> clients;
        Clients client;
        try {
            log.debug("Getting Client Record for id: {}", id);
            Select s = QueryBuilder.select().from("clients");
            s.where(QueryBuilder.eq(SchemaConstants.Common.Columns.Id, id));
            client = ci.getCassandraOperations().queryForObject(s, new ClientRowMapper()); // .queryForObject(s, new ClientRowMapper());
//            client = clients.size() != 0 ? clients.get(0) : null;
        } catch (Exception e) {
            log.error("Error updating or inserting Client Record - {}", e.getMessage());
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
    public Clients get(String clientName) throws DaoException {

        Clients client = null;
        try {
            Select s = QueryBuilder.select().from(ClientsTable.NAME);
            s.where(QueryBuilder.eq(ClientsTable.Columns.ClientName, clientName));
            client = ci.getCassandraOperations().queryForObject(s, new ClientRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return client;
    }





    @Override
    public List<ClientVehicles> getClientVehicles(UUID clientId) {
        return null;
    }
}
