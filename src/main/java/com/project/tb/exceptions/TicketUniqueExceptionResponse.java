package com.project.tb.exceptions;
public class TicketUniqueExceptionResponse {
	 private String name;
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		public TicketUniqueExceptionResponse(String name) {
	        this.name=name;
	    }
}
