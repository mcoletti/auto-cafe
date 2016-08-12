package com.saat.auto.cafe.common.exceptions;

/**
 * Created by mcoletti on 5/17/16.
 */
public class ClientVehicleException extends Exception {

    public ClientVehicleException() {
        super();
    }

    public ClientVehicleException(String message) {
        super(message);
    }

    public ClientVehicleException(Throwable cause) {
        super(cause);
    }
}

