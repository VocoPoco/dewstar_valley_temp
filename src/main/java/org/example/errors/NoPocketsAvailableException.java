package org.example.errors;

public class NoPocketsAvailableException extends Exception{
    public NoPocketsAvailableException(String message) {
        super(message);
    }
}
