package com.project.tb.models;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
public class Ticket extends AuditModel{
	 @Id
	    @GeneratedValue
private Long id;
	 @OneToOne
	 @JoinColumn(name="gameId")
private Game game;
private int price;
private String section;
private boolean returnable;
private Date returnDate;
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "userId", nullable = false)
@OnDelete(action = OnDeleteAction.CASCADE)
private User user;
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
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
