package com.project.tb.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.*;
import org.springframework.http.HttpStatus;
@Service
public class MapValidationErrorService{
    public ResponseEntity<?> mapValidationErrorService(BindingResult result){
   if (result.hasErrors()) {
	   Map<String, String> errorMap =new HashMap<>();
	   for (int i=0; i<result.getFieldErrorCount();i++){
		   errorMap.put(result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
	   }
	   return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
   }
   return null;
   }
}