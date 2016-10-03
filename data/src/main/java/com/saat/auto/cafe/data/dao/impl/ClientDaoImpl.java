package com.saat.auto.cafe.data.dao.impl;

import com.saat.auto.cafe.common.entitys.Client;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.interfaces.daos.ClientDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by micahcoletti on 10/2/16.
 */
@Component
public class ClientDaoImpl implements ClientDao {

    private static final Logger log = LoggerFactory.getLogger(ClientDaoImpl.class);

    private final CassandraInstance ci;

    @Autowired
    public ClientDaoImpl(CassandraInstance ci) {
        this.ci = ci;
    }

    @Override
    public Client upsert(Client client) {
        return null;
    }

    @Override
    public Client get(UUID id) {
        return null;
    }

    @Override
    public Client get(String name) {
        return null;
    }
}
