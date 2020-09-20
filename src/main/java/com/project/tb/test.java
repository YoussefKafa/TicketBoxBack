package com.project.tb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javassist.expr.NewArray;

public class test {

	public static void main(String[] args) throws ParseException {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);  
	    System.out.println(strDate);
	
	}

}
