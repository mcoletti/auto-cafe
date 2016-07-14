package com.saat.auto.cafe.data.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;

import org.apache.commons.configuration.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

/**
 * Created by micahcoletti on 7/12/16.
 */
public class CassandraInstanceImpl implements CassandraInstance {


    private final Configuration config;
    private final Cluster cluster;
    private final Session session;
    private final CassandraOperations operations;

    public CassandraInstanceImpl(Configuration config) {
        this.config = config;

        // Setup the Cluster
        String[] contactPoints = config.getString("contactPoints").split(",");
        Cluster.Builder builder = Cluster.builder();
        for (String contactPoint : contactPoints) {
            builder.addContactPoint(contactPoint);
        }
        cluster = builder.build();

        // Setup the Session
        session = cluster.connect(config.getString("keyspace"));
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
    public CassandraOperations getCassandraOperations() {
        return operations;
    }
}
