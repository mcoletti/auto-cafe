package com.saat.auto.cafe.common.models;


import com.saat.auto.cafe.common.entitys.Contact;
import com.saat.auto.cafe.common.entitys.DealerShip;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by micahcoletti on 7/22/16.
 */
@ApiModel(
        description = "THe DealerShip Model"
)
@Data

public class DealerShipModel {

    @ApiModelProperty(
            position = 1,
            value = "The DealerShip Id"
    )
    private String id;
    @ApiModelProperty(
            position = 2,
            required = true,
            value = "The DealerShip Client Id"
    )
    private String clientId;
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "The DealerShip Name"
    )
    private String name;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The DealerShip Contacts"
    )
    private List<ContactModel> contacts;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The Web Page Header Title"
    )
    private String headerTitle;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The Header Image Url"
    )
    private String headerImgUrl;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The Main Welcome Message"
    )
    private String homeWelcomeMessage;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The Main Welcome Message"
    )
    private Map<String,Integer> makeVehicleTotals;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "The location information, Address etc.."
    )
    private LocationDetailModel locationDetail;

    @ApiModelProperty(
            position = 5,
            required = true,
            value = "Who Created the Record in the DB"
    )
    private String createdUser;
    @ApiModelProperty(
            position = 6,
            required = true,
            value = "The Create DateTime"
    )
    private String createdDtm;
    @ApiModelProperty(
            position = 7,
            required = true,
            value = "Who Modified the Record in the DB"
    )
    private String modifiedBy;
    @ApiModelProperty(
            position = 8,
            required = true,
            value = "Modified DateTime"
    )
    private String modifiedDtm;

    /**
     * Convert to the DealerShip Entity object
     * @return
     */
    public DealerShip toEntity() {


        DealerShip dealerShip = new DealerShip();
        dealerShip.setId(UUID.fromString(id));
        dealerShip.setClientId(UUID.fromString(clientId));
        dealerShip.setName(name);

        List<Contact> contactList = new ArrayList<>();
        contacts.forEach(contact -> contactList.add(contact.toEntity()));
        dealerShip.setContacts(contactList);
        dealerShip.setHeaderTitle(headerTitle);
        dealerShip.setHeaderImgUrl(headerImgUrl);
        dealerShip.setHomeWelcomeMessage(homeWelcomeMessage);
        dealerShip.setMakeVehicleTotals(makeVehicleTotals);
        dealerShip.setLocationDetail(locationDetail.toEntity());
        dealerShip.setCreatedUser(createdUser);
        DateTime created = DateTime.parse(createdDtm);
        dealerShip.setCreated(UUIDGen.getTimeUUID(created.getMillis()));
        dealerShip.setModifiedUser(modifiedBy);
        DateTime modified = DateTime.parse(modifiedDtm);
        dealerShip.setModified(UUIDGen.getTimeUUID(modified.getMillis()));

        return dealerShip;
    }
}
