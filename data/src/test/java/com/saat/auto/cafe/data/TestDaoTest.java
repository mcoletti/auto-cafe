package com.saat.auto.cafe.data;

// import com.datastax.driver.core.Cluster;
// import com.datastax.driver.core.ResultSet;
// import com.datastax.driver.core.Session;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.saat.auto.cafe.common.models.Address;
import com.saat.auto.cafe.common.models.ClientLocations;
import com.saat.auto.cafe.common.models.Clients;
import com.saat.auto.cafe.common.models.Location;
import com.saat.auto.cafe.data.dao.mappers.ClientRowMapper;

import org.joda.time.DateTime;
import org.springframework.cassandra.core.WriteOptions;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by mcoletti on 6/1/16.
 */
public class TestDaoTest {



    @Test
    public void testGetTestModel() throws ClassNotFoundException, SQLException {




        Cluster cluster = Cluster.builder().addContactPoint("172.20.92.13").build();

        Session session = cluster.connect("autocafedb");

        CassandraOperations operations = new CassandraTemplate(session);

        Clients clients = new Clients();
        UUID id = UUID.randomUUID();

        clients.setId(id);
        clients.setCreatedBy("testUser");
        clients.setClientName("testClient");
        clients.setCreatedDtm(DateTime.now().toDate());
        clients.setModifiedBy("testUser");
        clients.setModifiedDtm(DateTime.now().toDate());
        operations.insert(clients);
        Select s = QueryBuilder.select().from("clients");
        s.where(QueryBuilder.eq("id",id));

        Clients clients1 = operations.queryForObject(s,new ClientRowMapper());
        UUID t2 = clients1.getId();


        Address address = new Address();
        address.setStreet1("12345");
        address.setStreet2("na");
        address.setCity("provo");
        address.setZipCode(84604);

        Location location = new Location();
        location.setAddress(address);
        location.setName("Provo Utah Office");

        ClientLocations cls = new ClientLocations();
        cls.setClientId(clients1.getId());
        cls.setLocation(location);

        Insert insert = QueryBuilder.insertInto("autocafedb","client_locations")
                .value("client_id",cls.getClientId())
                .value("location",location.toUdtValue(cluster));


        operations.execute(insert);



    }

}