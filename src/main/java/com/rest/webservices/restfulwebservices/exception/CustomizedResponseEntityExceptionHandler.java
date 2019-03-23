package com.rest.webservices.restfulwebservices.exception;

import com.rest.webservices.restfulwebservices.user.UserNotFoundException;
import com.rest.webservices.restfulwebservices.user.UserSaveException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object>  handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                new Date(),
                request.getDescription(false),
                ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object>  handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                new Date(),
                request.getDescription(false),
                ex.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserSaveException.class)
    public final ResponseEntity<Object>  handleSaveUserException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                new Date(),
                request.getDescription(false),
                ex.getMessage());

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ExceptionResponse exceptionResponse =  new ExceptionResponse(
                new Date(),
                ex.getBindingResult().getAllErrors().toString(),
                "Validation failed");

        return  new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
