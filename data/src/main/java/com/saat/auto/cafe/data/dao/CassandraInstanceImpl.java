package com.saat.auto.cafe.data.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.ApplicationProps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by micahcoletti on 7/12/16.
 */
@Component
public class CassandraInstanceImpl implements CassandraInstance {


    private final Cluster cluster;
    private final Session session;
    private ApplicationProps config;

    @Autowired
    public CassandraInstanceImpl(ApplicationProps config) {
        // Setup the Cluster
        String[] contactPoints = config.getContactPoints().split(",");
        Cluster.Builder builder = Cluster.builder();
        for (String contactPoint : contactPoints) {
            builder.addContactPoint(contactPoint);
        }
        cluster = builder.build();

        // Setup the Session
        session = cluster.connect(config.getKeySpace());
//        operations = new CassandraTemplate(session);

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
    public UDTValue getUdtValue(String udtType) {
        return cluster.getMetadata().getKeyspace(config.getKeySpace()).getUserType(udtType).newValue();
    }
}
