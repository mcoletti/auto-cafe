package com.saat.auto.cafe.common.models;

import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.Contact;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * Created by micahcoletti on 10/11/16.
 */
@Builder
@Data
@ApiModel(description = "The Contact Data Model")
public class ContactModel {
    private String firstName;
    private String lastName;
    private AddressModel address;

    /**
     * Convert the Model to an entity object
     * @return
     */
    public Contact toEntity(){
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setLastName(lastName);
        contact.setAddress(address.toEntity());
        return contact;
    }
}
