package com.github.akshay.datastore.exceptions;

public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException() {
        super("invalid operation performed");
    }

    public InvalidOperationException(String message) {
        super(message);
    }
}
