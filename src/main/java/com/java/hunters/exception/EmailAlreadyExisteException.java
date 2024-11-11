package com.java.hunters.exception;

public class EmailAlreadyExisteException extends RuntimeException {

    public EmailAlreadyExisteException(String message) {
        super(message);
    }
}
