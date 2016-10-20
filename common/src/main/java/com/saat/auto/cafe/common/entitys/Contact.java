package com.saat.auto.cafe.common.entitys;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.UDT;
import com.saat.auto.cafe.common.models.ContactModel;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by micahcoletti on 10/2/16.
 */
@UDT(name = "contact", keyspace = "autocafe")
@Data
@NoArgsConstructor
public class Contact {

    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    @Field
    @Frozen
    private Address address;


    public ContactModel toModel() {

        return ContactModel.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address.toModel()).build();
    }
}
