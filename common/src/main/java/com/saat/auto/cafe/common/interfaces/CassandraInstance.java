package com.saat.auto.cafe.common.interfaces;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import org.springframework.data.cassandra.core.CassandraOperations;

/**
 * Created by micahcoletti on 7/12/16.
 */
public interface CassandraInstance {


    Cluster getCluster();
    Session getSession();
    CassandraOperations getCassandraOperations();


}
