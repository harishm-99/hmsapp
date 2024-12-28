package com.hmsapp.exception;

import com.hmsapp.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ErrorDetails> handleUserCheckException(
            ResourceAlreadyExists e,
            WebRequest request
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                request.getDescription(true),
                new Date()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFound e,
            WebRequest request
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                request.getDescription(true),
                new Date()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception e,
            WebRequest request
    ){
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                request.getDescription(true),
                new Date()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
