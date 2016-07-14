package com.saat.auto.cafe.data.utils;

import com.contrastsecurity.cassandra.migration.CassandraMigration;
import com.contrastsecurity.cassandra.migration.config.Keyspace;
import com.contrastsecurity.cassandra.migration.config.ScriptsLocations;
import com.saat.auto.cafe.common.utils.PropertyFactory;

import org.apache.commons.configuration.Configuration;

/**
 * Created by mcoletti on 6/15/16.
 */
public class Migration {

    public static void main(String[] args){


        Configuration config = null;
        try {
            config = PropertyFactory.getConfig(Migration.class.getClassLoader().getResource("app.properties").getPath());
            Keyspace keyspace = new Keyspace();
            keyspace.setName(config.getString("keyspace"));
            String[] contactPoints = config.getStringArray("contactPoints");
            keyspace.getCluster().setContactpoints(contactPoints);
            keyspace.getCluster().setPort(config.getInt("dbPort"));
            keyspace.getCluster().setUsername(config.getString("dbUser"));
            keyspace.getCluster().setPassword(config.getString("dbPass"));


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
