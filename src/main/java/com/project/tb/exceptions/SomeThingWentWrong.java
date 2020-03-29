package com.project.tb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SomeThingWentWrong extends RuntimeException{
	public SomeThingWentWrong(String message) {
        super(message);
    }
}
