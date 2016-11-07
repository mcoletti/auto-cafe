package com.saat.auto.cafe.data.dao.accessors;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.saat.auto.cafe.common.entitys.Client;

import java.util.UUID;

/**
 * Created by micahcoletti on 10/6/16.
 */
@Accessor
public interface ClientAccessor {

    @Query("select * from clients where id = ?")
    Result<Client> qryById(UUID clientId);

    @Query("select * from clients where name = ?")
    Result<Client> qryByName(String name);

    @Query("select * from clients")
    Result<Client> qryForAll();
}
