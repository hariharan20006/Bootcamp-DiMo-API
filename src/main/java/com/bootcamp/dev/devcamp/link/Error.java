package com.bootcamp.dev.devcamp.link;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorCode errorCode;
}