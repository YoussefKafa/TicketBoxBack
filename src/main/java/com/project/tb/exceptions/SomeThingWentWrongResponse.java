package com.project.tb.exceptions;
public class SomeThingWentWrongResponse {
private String message;
public SomeThingWentWrongResponse(String msg) {
	this.message=msg;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
