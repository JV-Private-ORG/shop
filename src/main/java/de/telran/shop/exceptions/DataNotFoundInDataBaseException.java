package de.telran.shop.exceptions;

public class DataNotFoundInDataBaseException extends RuntimeException{

    public DataNotFoundInDataBaseException(String message) {
        super(message);
    }
}
