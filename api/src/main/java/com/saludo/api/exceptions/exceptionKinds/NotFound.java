package com.saludo.api.exceptions.exceptionKinds;

public class NotFound extends RuntimeException{
    public NotFound(String message) {
        super(message);
    }
}
