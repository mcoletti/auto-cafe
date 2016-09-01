package com.saat.auto.cafe.data.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.CodecRegistry;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.mapping.MappingManager;
import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.AddressCodec;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.data.DbProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by micahcoletti on 7/12/16.
 */
@Component
public class CassandraInstanceImpl implements CassandraInstance {


    private final Cluster cluster;
    private final Session session;
    private final DbProperties config;
    private final MappingManager mappingManager;

    @Autowired
    public CassandraInstanceImpl(DbProperties config) {
        this.config = config;
        // Setup the Cluster
        String[] contactPoints = config.getContactPoints().split(",");
        Cluster.Builder builder = Cluster.builder();
        for (String contactPoint : contactPoints) {
            builder.addContactPoint(contactPoint);
        }
        CodecRegistry codecRegistry = new CodecRegistry();

        cluster = builder
                .withCodecRegistry(codecRegistry)
                .build();

//        // Add the Address custom codec for the address user type
        UserType addressType = cluster.getMetadata().getKeyspace(config.getKeySpace()).getUserType("address");
        TypeCodec<UDTValue> addressTypeCodec = codecRegistry.codecFor(addressType);
        AddressCodec addressCodec = new AddressCodec(addressTypeCodec, Address.class);
        codecRegistry.register(addressCodec);


        // Setup the Session

        session = cluster.connect(config.getKeySpace());
        mappingManager = new MappingManager(session);



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

    @Override
    public MappingManager mappingManager() {
        return mappingManager;
    }
}
