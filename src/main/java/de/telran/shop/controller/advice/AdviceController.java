package de.telran.shop.controller.advice;

import de.telran.shop.exceptions.DataNotFoundInDataBaseException;
import de.telran.shop.exceptions.InsufficientDataException;
import de.telran.shop.exceptions.InvalidValueExeption;
import de.telran.shop.exceptions.WrongIdException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(InvalidValueExeption.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(InvalidValueExeption exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(DataNotFoundInDataBaseException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(DataNotFoundInDataBaseException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(WrongIdException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(WrongIdException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(InsufficientDataException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(InsufficientDataException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }

    // по умолчанию для всех остальных исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body(new ErrorMessage("Sorry, something went wrong!"));
    }

}