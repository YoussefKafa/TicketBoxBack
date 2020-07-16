package com.project.tb.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.tb.exceptions.PasswordIsntCorrectException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*
 * can use the following code to create @ControllerAdvice class to handle the exceptions globally −
 */
@ControllerAdvice
   public class ExceptionController {
	 @ExceptionHandler(value = PasswordIsntCorrectException.class)
	   public ResponseEntity<Object> exception(PasswordIsntCorrectException exception) {
	      return new ResponseEntity<>("{message:\"Password isn't corrent\"}", HttpStatus.UNAUTHORIZED);
	   }
}
