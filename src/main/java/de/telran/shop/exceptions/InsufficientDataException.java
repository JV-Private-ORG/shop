package de.telran.shop.exceptions;

public class InsufficientDataException extends RuntimeException{

    public InsufficientDataException(String message) {
        super(message);
    }
}