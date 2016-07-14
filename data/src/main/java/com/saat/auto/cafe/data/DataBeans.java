package com.saat.auto.cafe.data;

// import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.saat.auto.cafe.common.interfaces.CassandraInstance;
import com.saat.auto.cafe.common.utils.PropertyFactory;
import com.saat.auto.cafe.data.dao.CassandraInstanceImpl;

import org.apache.commons.configuration.Configuration;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Created by mcoletti on 6/7/16.
 */
@org.springframework.context.annotation.Configuration
public class DataBeans {

    @Bean
    Configuration getConfig() {


        Configuration configuration = null;
        try {
            configuration = PropertyFactory.getConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

//    @Bean
//    public DataSource getDataSource() throws SQLException {
//
//        Configuration config = getConfig();
//
//
//
//
//
//        PGPoolingDataSource dataSource = new PGPoolingDataSource();
//
//        dataSource.setUser(config.getString("dbUser"));
//        dataSource.setServerName(config.getString("dbHost"));
//        dataSource.setPassword(config.getString("dbPass"));
//        dataSource.setDatabaseName(config.getString("dbSchema"));
//        dataSource.setPortNumber(config.getInt("dbPort"));
//        //
//        // MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
//        // dataSource.setServerName(config.getString("dbHost"));
//        // dataSource.setUser(config.getString("dbUser"));
//        // dataSource.setPassword(config.getString("dbPass"));
//        // dataSource.setDatabaseName(config.getString("dbSchema"));
//        // dataSource.setPort(config.getInt("dbPort"));
//
//
//        return dataSource;
//    }


    @Bean
    public CassandraInstance getCassandraInstance(){
        CassandraInstance instance = new CassandraInstanceImpl(getConfig());
        return instance;
    }


}
