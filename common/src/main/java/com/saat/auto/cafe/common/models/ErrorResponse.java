package com.saat.auto.cafe.common.models;

import lombok.Data;

/**
 * Created by micahcoletti on 11/22/16.
 */
@Data
public class ErrorResponse {
    private String errorMessage;
    private String httpStatus;
}
