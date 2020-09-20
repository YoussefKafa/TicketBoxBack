package com.project.tb.payload;

public class ReturnTicketRequest {
	String userId;
	String ticketId;
	String returnDate;
	String brId;
	int price;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrId() {
		return brId;
	}
	public void setBrId(String brId) {
		this.brId = brId;
	}
	
}
