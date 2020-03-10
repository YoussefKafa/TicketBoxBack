package com.project.tb.exceptions;

public class StadiumUniqueExceptionResponse {
	 private String name;
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		public StadiumUniqueExceptionResponse(String name) {
	        this.name=name;
	    }

}
