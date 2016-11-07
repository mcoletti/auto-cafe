package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.ClientModel;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by micahcoletti on 10/1/16.
 */
@Table(
        name = "clients",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class Client {

    @PartitionKey
    @Column
    private UUID id;
    @Column
    private String name;
    @Column(name = "location_detail")
    private LocationDetail locationDetails;
    @Column(name = "home_page_text")
    private String homePageText;
    @Column
    private UUID created;
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column
    private UUID modified;

    /**
     * Convert to Model Object
     * @return
     */
    public ClientModel toModel(){

        ClientModel clientModel = new ClientModel();
        clientModel.setId(id.toString());
        clientModel.setName(name);
        clientModel.setLocationDetails(locationDetails.toModel());
        clientModel.setHomePageText(homePageText);
        clientModel.setCreated(new DateTime(UUIDGen.unixTimestamp(created)).toString());
        clientModel.setCreatedUser(createdUser);
        clientModel.setModified(new DateTime(UUIDGen.unixTimestamp(modified)).toString());
        clientModel.setModifiedUser(modifiedUser);
        return clientModel;
    }

}
