package com.bootcamp.dev.devcamp.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ErrorCode {

    UNKNOWN(999),
    UNAUTHORIZED(1000),
    INVALID_REQUEST_BODY(1001),
    INVALID_CREDENTIALS(1002),
    FORBIDDEN(1003),
    NOT_FOUND(1004);


    private int value;

    ErrorCode(int value) {
        this.value = value;
    }

    // Adding @JsonValue annotation that tells the 'value' to be of integer type while de-serializing.
    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static ErrorCode getNameByValue(int value) {
        return Arrays.stream(ErrorCode.values())
                .filter(errorCode -> errorCode.value == value)
                .findAny()
                .orElse(ErrorCode.UNKNOWN);
    }
}