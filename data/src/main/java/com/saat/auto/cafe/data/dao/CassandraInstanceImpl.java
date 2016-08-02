package com.saat.auto.cafe.data.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.data.CassandraProps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by micahcoletti on 7/12/16.
 */
@Component(value = "main")
public class CassandraInstanceImpl implements CassandraInstance {


    private final Cluster cluster;
    private final Session session;
    private final CassandraOperations operations;
    private CassandraProps config;

    @Autowired
    public CassandraInstanceImpl(CassandraProps config) {
        // Setup the Cluster
        String[] contactPoints = config.getContactPoints().split(",");
        Cluster.Builder builder = Cluster.builder();
        for (String contactPoint : contactPoints) {
            builder.addContactPoint(contactPoint);
        }
        cluster = builder.build();

        // Setup the Session
        session = cluster.connect(config.getKeySpaceName());
        operations = new CassandraTemplate(session);

    }


    @Override
    public Cluster getCluster() {

        return cluster;
    }

    @Override
    public Session getSession() {

        return session;
    }

    @Override
    public CassandraOperations getCassandraTemplate() {
        return operations;
    }
}
