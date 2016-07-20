package com.saat.auto.cafe.common.exceptions;

/**
 * Created by mcoletti on 5/17/16.
 */
public class VehicleDetailsException extends Exception {

    public VehicleDetailsException() {
        super();
    }

    public VehicleDetailsException(String message) {
        super(message);
    }

    public VehicleDetailsException(Throwable cause) {
        super(cause);
    }
}

