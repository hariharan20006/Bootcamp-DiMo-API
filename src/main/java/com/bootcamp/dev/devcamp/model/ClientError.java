package com.bootcamp.dev.devcamp.model;


import com.bootcamp.dev.devcamp.link.ErrorCode;
import com.bootcamp.dev.devcamp.link.ErrorRepresentation;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClientError extends Throwable {

    private HttpStatus httpStatus;
    private ErrorRepresentation error;

    public ClientError(HttpStatus httpStatus, ErrorRepresentation errorRepresentation) {
        this.httpStatus = httpStatus;
        error = errorRepresentation;
    }

    private static final String INVALID_BODY_ERROR = "Invalid fields specified in Body";

    public static ClientError invalidBody() {
        return new ClientError(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorRepresentation(INVALID_BODY_ERROR, ErrorCode.INVALID_REQUEST_BODY));
    }
}
