package com.project.tb.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Lob;

public class Ticket {
private Long id;
private Game game;
private int price;
private String section;
private boolean returnable;
private Date returnDate;
@Lob
@Column(name="QRcode", nullable=false, columnDefinition="mediumblob") //medium is 16mb
private byte[] QRcode;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Game getGame() {
	return game;
}
public void setGame(Game game) {
	this.game = game;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getSection() {
	return section;
}
public void setSection(String section) {
	this.section = section;
}
public boolean isReturnable() {
	return returnable;
}
public void setReturnable(boolean returnable) {
	this.returnable = returnable;
}
public Date getReturnDate() {
	return returnDate;
}
public void setReturnDate(Date returnDate) {
	this.returnDate = returnDate;
}
}
