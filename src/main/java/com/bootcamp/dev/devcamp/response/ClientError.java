package com.bootcamp.dev.devcamp.response;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientError extends Throwable {

    private static final String INVALID_BODY_ERROR = "Invalid fields specified in Body";
    private static final String USER_ALREADY_EXISTS = "User Already Exists";
    private static final String BAD_CREDENTIALS = "Bad Credentials";
    private static final String UNAUTHORIZED = "User not authorized";

    private final HttpStatus httpStatus;
    private final ErrorRepresentation errorRepresentation;


    public ClientError(HttpStatus httpStatus, ErrorRepresentation errorRepresentation) {
        this.httpStatus = httpStatus;
        this.errorRepresentation = errorRepresentation;
    }

    public static ClientError invalidBody() {
        return new ClientError(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorRepresentation(new Error(ErrorCode.INVALID_REQUEST_BODY, INVALID_BODY_ERROR)));
    }

    public static ClientError userAlreadyExists() {
        return new ClientError(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorRepresentation(new Error(ErrorCode.INVALID_REQUEST_BODY, USER_ALREADY_EXISTS)));
    }

    public static ClientError badCredentials() {
        return new ClientError(HttpStatus.BAD_REQUEST, new ErrorRepresentation(new Error(ErrorCode.INVALID_CREDENTIALS, BAD_CREDENTIALS)));
    }

    public static ClientError unauthorized() {
        return new ClientError(HttpStatus.UNAUTHORIZED, new ErrorRepresentation(new Error(ErrorCode.INVALID_CREDENTIALS, BAD_CREDENTIALS)));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClientError)) {
            return false;
        }
        return ((ClientError) obj).httpStatus == this.httpStatus;
    }
}
