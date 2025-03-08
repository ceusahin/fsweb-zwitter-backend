package com.example.zwitter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends ZwitterException{
    public NotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
