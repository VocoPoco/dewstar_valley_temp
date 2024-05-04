package org.example.errors;

public class PocketIsEmptyException extends Exception{
    public PocketIsEmptyException(String message) {
        super(message);
    }
}
