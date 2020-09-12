package com.project.tb.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ModelException extends RuntimeException{
	 public ModelException(String message) {
	        super(message);
	    }
}
