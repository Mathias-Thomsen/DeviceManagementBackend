package com.example.devicemanagement.exceptions;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }

}
