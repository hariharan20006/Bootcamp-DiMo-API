package com.bootcamp.dev.devcamp.response;


public enum ErrorCode {

    INVALID_REQUEST_BODY(1001);

    private int value;

    ErrorCode(int value) {
        this.value = value;
    }
}