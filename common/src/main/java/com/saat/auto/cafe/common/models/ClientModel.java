package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.Client;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;

import java.util.UUID;

import lombok.Data;

/**
 * Created by micahcoletti on 11/3/16.
 */
@Data
public class ClientModel {
    private String id;
    private String name;
    private LocationDetailModel locationDetails;
    private String homePageText;
    private String created;
    private String createdUser;
    private String modifiedUser;
    private String modified;


    /**
     * Convert to Entity
     * @return
     */
    public Client toEntity(){

        Client client = new Client();
        client.setId(UUID.fromString(id));
        client.setName(name);
        client.setLocationDetails(locationDetails.toEntity());
        client.setHomePageText(homePageText);
        client.setCreatedUser(createdUser);
        client.setCreated(UUIDGen.getTimeUUID(DateTime.parse(created).getMillis()));
        client.setModifiedUser(modifiedUser);
        client.setModified(UUIDGen.getTimeUUID(DateTime.parse(modified).getMillis()));
        return client;
    }
}
