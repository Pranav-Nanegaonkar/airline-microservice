package com.airline.locationservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus status;

    ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public static ApiException notFound(String msg) {
        return new ApiException(HttpStatus.NOT_FOUND, msg);
    }
    public static ApiException badRequest(String msg) {
        return new ApiException(HttpStatus.BAD_REQUEST, msg);
    }
    public static ApiException unauthorized(String msg) {
        return new ApiException(HttpStatus.UNAUTHORIZED, msg);
    }
    public static ApiException forbidden(String msg) {
        return new ApiException(HttpStatus.FORBIDDEN, msg);
    }
}
