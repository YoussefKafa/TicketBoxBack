package com.project.tb.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST) //to make sure every time it happened 
//it gives the user a http bad request code
public class GameUniqueException extends RuntimeException{ //because it is a runtime exception
    public GameUniqueException(String message) {
        super(message);
    }
}