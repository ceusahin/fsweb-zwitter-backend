package com.example.zwitter.exceptions;

import org.springframework.http.HttpStatus;

public class ZwitterException extends RuntimeException {
    private HttpStatus status;

    public ZwitterException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}