package com.saludo.api.exceptions.exceptionKinds;

public class NonExistentContactException extends RuntimeException {
    public NonExistentContactException(String message) {
        super(message);
    }
}
