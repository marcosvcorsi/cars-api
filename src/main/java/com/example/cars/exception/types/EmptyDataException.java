package com.example.cars.exception.types;

public class EmptyDataException extends RuntimeException {

    public EmptyDataException() {
        super();
    }

    public EmptyDataException(String message) {
        super(message);
    }
}
