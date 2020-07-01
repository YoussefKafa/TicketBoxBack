package com.project.tb.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST) //if this happens then 400 error
public class UserException extends RuntimeException{
	 public UserException(String message) {
	        super(message);
	    }
}
