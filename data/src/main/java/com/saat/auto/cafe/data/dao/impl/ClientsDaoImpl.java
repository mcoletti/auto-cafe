package com.saat.auto.cafe.data.dao.impl;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.saat.auto.cafe.common.db.SchemaConstants;
import com.saat.auto.cafe.common.db.SchemaConstants.ClientsTable;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.ClientsDao;
import com.saat.auto.cafe.common.models.ClientLocations;
import com.saat.auto.cafe.common.models.ClientVehicles;
import com.saat.auto.cafe.common.models.Clients;
import com.saat.auto.cafe.data.DataBeans;
import com.saat.auto.cafe.data.dao.mappers.ClientRowMapper;

import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by mcoletti on 6/16/16.
 */
@ContextConfiguration(classes = DataBeans.class)
public class ClientsDaoImpl implements ClientsDao {


    @Inject
    private CassandraInstance ci;

    public ClientsDaoImpl(CassandraInstance cassandraInstance) {
        this.ci = cassandraInstance;
    }


    @Override
    public Clients upsert(Clients clients) {
        ci.getCassandraOperations().insert(clients);
        return clients;
    }

    @Override
    public Clients get(UUID id) {

        Select s = QueryBuilder.select().from("clients");
        s.where(QueryBuilder.eq(SchemaConstants.Common.Columns.Id,id));
        Clients client = ci.getCassandraOperations().queryForObject(s,new ClientRowMapper());
        return client;
    }

    @Override
    public Clients get(String clientName) {

        Select s = QueryBuilder.select().from(ClientsTable.NAME);
        s.where(QueryBuilder.eq(ClientsTable.Columns.ClientName,clientName));
        Clients client = ci.getCassandraOperations().queryForObject(s,new ClientRowMapper());
        return client;
    }

    @Override
    public List<ClientLocations> getClientLocations(UUID clientId) {

        Select s = QueryBuilder.select().from(SchemaConstants.ClientsLocationTable.NAME);
        s.where(QueryBuilder.eq(SchemaConstants.ClientsLocationTable.Columns.ClientId,clientId));
        Clients client = ci.getCassandraOperations().queryForObject(s,new ClientRowMapper());

        return null;
    }

    @Override
    public List<ClientVehicles> getClientVehicles(UUID clientId) {
        return null;
    }
}
