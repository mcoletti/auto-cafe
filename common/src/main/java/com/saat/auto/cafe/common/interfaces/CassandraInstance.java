package com.saat.auto.cafe.common.interfaces;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;


/**
 * Created by micahcoletti on 7/12/16.
 */
public interface CassandraInstance {


    Cluster getCluster();
    Session getSession();
//    CassandraOperations getCassandraTemplate();
    UDTValue getUdtValue(String udtType);


}
