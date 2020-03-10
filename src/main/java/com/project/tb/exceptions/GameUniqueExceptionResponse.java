package com.project.tb.exceptions;
public class GameUniqueExceptionResponse {
	 private String name;
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		public GameUniqueExceptionResponse(String name) {
	        this.name=name;
	    }
}
