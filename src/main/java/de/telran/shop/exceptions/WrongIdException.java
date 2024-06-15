package de.telran.shop.exceptions;

public class WrongIdException extends RuntimeException{

    public WrongIdException(String message) {
        super(message);
    }
}
