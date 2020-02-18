package com.bootcamp.dev.devcamp.response;


public enum ErrorCode {

    INVALID_REQUEST_BODY(1001),
    INVALID_CREDENTIALS(1002);

    private int value;

    ErrorCode(int value) {
        this.value = value;
    }
}