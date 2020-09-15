package com.project.tb.payload;

public class TicketScanResult {
String name;
String ticketSequence;
String fullPath;
String email;
boolean confirmed;
public TicketScanResult(String name, String ticketSequence, String fullPath, String email, boolean confirmed) {
	this.name = name;
	this.ticketSequence = ticketSequence;
	this.fullPath = fullPath;
	this.email = email;
	this.confirmed = confirmed;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getFullPath() {
	return fullPath;
}
public void setFullPath(String fullPath) {
	this.fullPath = fullPath;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public boolean isConfirmed() {
	return confirmed;
}
public void setConfirmed(boolean confirmed) {
	this.confirmed = confirmed;
}
public String getTicketSequence() {
	return ticketSequence;
}
public void setTicketSequence(String ticketSequence) {
	this.ticketSequence = ticketSequence;
}

}
