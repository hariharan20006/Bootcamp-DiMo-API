package com.bootcamp.dev.devcamp.link;

import lombok.Getter;

@Getter
public class ErrorRepresentation {
    private Error error;

    public ErrorRepresentation(String message, ErrorCode errorCode) {
        this.error = new Error(message, errorCode);
    }
}
