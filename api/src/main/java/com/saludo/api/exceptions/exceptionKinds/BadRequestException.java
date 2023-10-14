package com.saludo.api.exceptions.exceptionKinds;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

}
