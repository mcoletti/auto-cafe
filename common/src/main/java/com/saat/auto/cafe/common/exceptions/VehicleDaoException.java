package com.saat.auto.cafe.common.exceptions;

/**
 * Created by mcoletti on 5/17/16.
 */
public class VehicleDaoException extends Exception {

    public VehicleDaoException() {
        super();
    }

    public VehicleDaoException(String message) {
        super(message);
    }

    public VehicleDaoException(Throwable cause) {
        super(cause);
    }
}

