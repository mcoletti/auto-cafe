package com.saat.auto.cafe.common.entitys;

import com.google.common.reflect.TypeToken;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.core.exceptions.InvalidTypeException;

import java.nio.ByteBuffer;

/**
 * Created by micahcoletti on 8/12/16.
 */
public class AddressCodec extends TypeCodec<Address> {

    private final TypeCodec<UDTValue> innerCodec;

    private final UserType userType;

    public AddressCodec(TypeCodec<UDTValue> innerCodec, Class<Address> javaType) {
        super(innerCodec.getCqlType(), javaType);
        this.innerCodec = innerCodec;
        this.userType = (UserType) innerCodec.getCqlType();
    }

//    protected AddressCodec(DataType cqlType, Class<Address> javaClass) {
//        super(cqlType, javaClass);
//        this.innerCodec = innerCodec;
//        this.userType = (UserType)innerCodec.getCqlType();
//    }

    @Override
    public ByteBuffer serialize(Address value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return innerCodec.serialize(toUDTValue(value), protocolVersion);
    }

    @Override
    public Address deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return toAddress(innerCodec.deserialize(bytes, protocolVersion));
    }

    @Override
    public Address parse(String value) throws InvalidTypeException {
        return value == null || value.isEmpty() || value.equals(null) ? null : toAddress(innerCodec.parse(value));
    }

    @Override
    public String format(Address value) throws InvalidTypeException {
        return value == null ? null : innerCodec.format(toUDTValue(value));
    }

    protected Address toAddress(UDTValue value) {
//        return value == null ? null : new Address(
//                value.getString("street1"),
//                value.getString("street2"),
//                value.getString("city"),
//                value.getString("state"),
//                value.getInt("zip_code"),
//                value.getSet("phones",String.class)
//        );
        Address a = null;
        if (value != null) {
            a = new Address();
            a.setStreet1(value.getString("street1"));
            a.setStreet2(value.getString("street2"));
            a.setCity(value.getString("city"));
            a.setState(value.getString("state"));
            a.setZipCode(value.getInt("zip_code"));
            a.setPhones(value.getSet("phones", String.class));
        }

        return a;
    }

    protected UDTValue toUDTValue(Address value) {
        return value == null ? null : userType.newValue()
                .setString("street1", value.getStreet1())
                .setString("street2", value.getStreet2())
                .setString("city", value.getCity())
                .setString("state", value.getState())
                .setInt("zip_code", value.getZipCode())
                .setSet("phones", value.getPhones());
    }
}
