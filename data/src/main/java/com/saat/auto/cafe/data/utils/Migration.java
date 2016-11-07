package com.saat.auto.cafe.data.utils;

import com.contrastsecurity.cassandra.migration.CassandraMigration;
import com.contrastsecurity.cassandra.migration.config.Keyspace;
import com.contrastsecurity.cassandra.migration.config.ScriptsLocations;
import com.saat.auto.cafe.data.MigrateProperties;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

//import org.apache.commons.configuration.Configuration;

/**
 * Created by mcoletti on 6/15/16.
 */

public class Migration {

    private static MigrateProperties props;


    public static void main(String[] args){
//        Configuration config = null;
        try {
            Yaml yaml = new Yaml();
            InputStream in = Migration.class.getClassLoader().getResourceAsStream("migrate.yml");
            props = yaml.loadAs(in, MigrateProperties.class);
            Keyspace keyspace = new Keyspace();
            keyspace.setName(props.getKeySpace());
            String[] contactPoints = props.getContactPoints().split(",");
            keyspace.getCluster().setContactpoints(contactPoints);
            keyspace.getCluster().setPort(props.getPort());
            keyspace.getCluster().setUsername(props.getUserName());
            keyspace.getCluster().setPassword(props.getPassword());


            CassandraMigration cm = new CassandraMigration();
            ScriptsLocations location = new ScriptsLocations("db/migration/cql");
            String[] scriptLocs = new String[1];
            scriptLocs[0] = location.getLocations().get(0).getPath();
            cm.getConfigs().setScriptsLocations(scriptLocs);
            cm.setKeyspace(keyspace);
            cm.migrate();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

}
