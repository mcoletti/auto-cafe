package com.saat.auto.cafe.common.interfaces.daos;

import com.saat.auto.cafe.common.entitys.Client;

import java.util.UUID;

/**
 * Created by micahcoletti on 10/2/16.
 */
public interface ClientDao {

    /**
     * Method that Adds or Updates a Client
     * @param client the client object to dd or Update
     * @return an instance of the Client object
     */
    Client upsert(Client client);

    /**
     * Method to get a Client based off Id
     * @param id the UUI of the client to get
     * @return an instance of the Client object
     */
    Client get(UUID id);

    /**
     * Method to get Client by name
     * @param name the name of the Client
     * @return an instance of the client object
     */
    Client get(String name);


}
