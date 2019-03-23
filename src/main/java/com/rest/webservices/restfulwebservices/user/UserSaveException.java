package com.rest.webservices.restfulwebservices.user;

import org.omg.CORBA.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UserSaveException extends RuntimeException{

    public UserSaveException(String message) {
        super(message);
    }
}
