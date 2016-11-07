package com.saat.auto.cafe.common.codecs;

import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.UserType;
import com.datastax.driver.core.exceptions.InvalidTypeException;
import com.saat.auto.cafe.common.entitys.Address;
import com.saat.auto.cafe.common.entitys.MakeVehicleTotal;

import java.nio.ByteBuffer;

/**
 * Created by micahcoletti on 11/2/16.
 */
public class MakeVehicleTotalCodec extends TypeCodec<MakeVehicleTotal> {

    private final TypeCodec<UDTValue> innerCodec;

    private final UserType userType;

    public MakeVehicleTotalCodec(TypeCodec<UDTValue> innerCodec, Class<MakeVehicleTotal> javaType) {
        super(innerCodec.getCqlType(), javaType);
        this.innerCodec = innerCodec;
        this.userType = (UserType) innerCodec.getCqlType();
    }

    @Override
    public ByteBuffer serialize(MakeVehicleTotal value, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return innerCodec.serialize(toUDTValue(value), protocolVersion);
    }

    @Override
    public MakeVehicleTotal deserialize(ByteBuffer bytes, ProtocolVersion protocolVersion) throws InvalidTypeException {
        return toMakeVehicleTotal(innerCodec.deserialize(bytes, protocolVersion));
    }

    @Override
    public MakeVehicleTotal parse(String value) throws InvalidTypeException {
        return value == null || value.isEmpty() || value.equals(null) ? null : toMakeVehicleTotal(innerCodec.parse(value));
    }

    @Override
    public String format(MakeVehicleTotal value) throws InvalidTypeException {
        return value == null ? null : innerCodec.format(toUDTValue(value));
    }

    protected MakeVehicleTotal toMakeVehicleTotal(UDTValue value){

        MakeVehicleTotal mvt =null;
        if(value != null){

            mvt = new MakeVehicleTotal();
            mvt.setMake(value.getString("make"));
            mvt.setVehicleTotal(value.getInt("vehicle_total"));
        }
        return mvt;
    }

    protected UDTValue toUDTValue(MakeVehicleTotal value) {
        return value == null ? null : userType.newValue()
                .setString("make", value.getMake())
                .setInt("vehicle_total", value.getVehicleTotal());
    }
}
