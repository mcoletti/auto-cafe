package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.saat.auto.cafe.common.AutoCafeConstants;
import com.saat.auto.cafe.common.models.ContactModel;
import com.saat.auto.cafe.common.models.DealerShipModel;

import org.apache.cassandra.utils.UUIDGen;
import org.joda.time.DateTime;
//import org.springframework.cassandra.core.PrimaryKeyType;
//import org.springframework.data.cassandra.mapping.Column;
//import org.springframework.data.cassandra.mapping.Indexed;
//import org.springframework.data.cassandra.mapping.PrimaryKey;
//import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
//
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.datastax.driver.core.querybuilder.QueryBuilder.eq;

/**
 * Created by mcoletti on 5/17/16.
 */
@Table(
        name = "dealerships",
        readConsistency = AutoCafeConstants.READ_CONSITENCY,
        writeConsistency = AutoCafeConstants.WRITE_CONSITENCY
)
@NoArgsConstructor
@Data
public class DealerShip {


    @PartitionKey
    @Column(name = "client_id")
    private UUID clientId;
    @PartitionKey(value = 1)
    @Column
    private UUID id;
    @Column
    private String name;
    @Column
    private List<Contact> contacts;
    @Column(name = "header_title")
    private String headerTitle;
    @Column(name = "header_img_url")
    private String headerImgUrl;
    @Column(name = "home_welcome_message")
    private String homeWelcomeMessage;
    @Column(name = "make_vehicle_totals")
    private Map<String,Integer> makeVehicleTotals;
    @Column(name = "location_detail")
    private LocationDetail locationDetail;
    @Column(name = "created_user")
    private String createdUser;
    @Column
    private UUID created;
    @Column(name = "modified_user")
    private String modifiedUser;
    @Column
    private UUID     modified;

    /**
     * Convert to the DealerShip Model
     * @return
     */
    public DealerShipModel toModel() {

        List<ContactModel> contactList = new ArrayList<>();
        contacts.forEach(contact -> contactList.add(contact.toModel()));

        long timestampCreated = UUIDGen.getAdjustedTimestamp(created);
        DateTime createDtm = new DateTime(timestampCreated);
        long timestampModified = modified != null ? UUIDGen.getAdjustedTimestamp(modified) : 0L;
        DateTime modifiedDtm = new DateTime(timestampModified);

        DealerShipModel dealerShipModel = new DealerShipModel();
        dealerShipModel.setId(id.toString());
        dealerShipModel.setClientId(clientId.toString());
        dealerShipModel.setName(name);
        dealerShipModel.setContacts(contactList);
        dealerShipModel.setHeaderTitle(headerTitle);
        dealerShipModel.setHeaderImgUrl(headerImgUrl);
        dealerShipModel.setHomeWelcomeMessage(homeWelcomeMessage);
        dealerShipModel.setLocationDetail(locationDetail.toModel());
        dealerShipModel.setCreatedUser(createdUser);
        dealerShipModel.setCreatedDtm(createDtm.toString());
        dealerShipModel.setModifiedBy(modifiedUser);
        dealerShipModel.setModifiedDtm(modifiedDtm.toString());

        return dealerShipModel;
    }
}
