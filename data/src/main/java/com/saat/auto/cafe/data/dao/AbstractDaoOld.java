package com.saat.auto.cafe.data.dao;


import com.saat.auto.cafe.common.utils.fluentsql.SQL;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import static com.saat.auto.cafe.common.utils.fluentsql.SQL.Statics.INSERT;
import static com.saat.auto.cafe.common.utils.fluentsql.SQL.Statics.UPDATE;

/**
 * Created by mcoletti on 4/28/16.
 */
public abstract class AbstractDaoOld extends JdbcDaoSupport {


    protected AbstractDaoOld(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    /**
     * Method that build up an Insert SQL Command
     *
     * @param tableName the name of the table
     * @param columns   an array of columns
     * @param params    an array of params object to pass into the SQL command
     */
    public SQL buildInsertCommand(String tableName, String[] columns, List<Object> params) {

        SQL sql = INSERT().INTO(tableName).openParen()
                .FIELD(columns).closeParen()
                .VALUES().openParen();
        int len = params.size();
        int i = 1;

        for (Object param : params) {
            if (i < len) {
                sql.VALUE(param).comma();
            } else {
                sql.VALUE(param).closeParen();
            }
            i++;
        }

        return sql;
    }

    /**
     * Method to build up a update SQL Command based of params
     *
     * @param tableName    the name the table
     * @param nameValueMap the nameValue map collection
     * @param id           the Id of the record to update
     */
    public SQL buildUpdateCommand(String tableName, Map<String, Object> nameValueMap, int id) {

        SQL sql = UPDATE(tableName).SET();
        int i = 1;
        int len = nameValueMap.size();
        for (Map.Entry<String, Object> map : nameValueMap.entrySet()) {
            if (i < len) {
                sql.FIELD(map.getKey()).EQUAL(map.getValue()).comma();
            } else {
                sql.FIELD(map.getKey()).EQUAL(map.getValue());
            }
            i++;

        }
        sql.WHERE().FIELD("Id").EQUAL(id);
        return sql;
    }
}
