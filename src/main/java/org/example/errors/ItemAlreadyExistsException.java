package org.example.errors;

public class ItemAlreadyExistsException extends Exception{
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
