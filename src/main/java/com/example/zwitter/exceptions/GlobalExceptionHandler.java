package com.example.zwitter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZwitterErrorResponse> handleException(ZwitterException zwitterException) {
        ZwitterErrorResponse zwitterErrorResponse = new ZwitterErrorResponse(
                zwitterException.getMessage(),
                zwitterException.getStatus().value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(zwitterErrorResponse, zwitterException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZwitterErrorResponse> handleException(Exception exception) {
        ZwitterErrorResponse zwitterErrorResponse = new ZwitterErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(zwitterErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
