package com.project.tb.exceptions;

public class TeamUniqueExceptionResponse {
	 private String name;
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		public TeamUniqueExceptionResponse(String name) {
	        this.name=name;
	    }
}
