package com.saludo.api.exceptions.exceptionKinds;

public class ContactAlreadyExistException extends RuntimeException{
    public ContactAlreadyExistException(String message) {
        super(message);
    }
}
