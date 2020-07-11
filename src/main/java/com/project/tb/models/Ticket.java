package com.project.tb.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.mapping.List;
import org.hibernate.mapping.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

//many to one with TicketsList

@Entity
public class Ticket extends DateAudit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "ticket_id",unique = true, nullable = false)
	private Long id;
	@Column(updatable = false)
	// we are going to use it to find an individual ticket in the TicketsList
	private String ticketSequence;
	private String[] gates;
	private String qrCode;
	private int price;
	private int counter;
	private boolean returnable;
	private String releaseDate;
	private String endDate;
	private String returnDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "game_id", updatable = true, nullable = true)
	private Game game;
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
    @JoinTable(
        name = "User_tickets", 
        joinColumns = { @JoinColumn(name = "ticket_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    java.util.Set<User> users =new HashSet<>();
	////////////////////////////////
	///////////////////////////////
	public java.util.Set<User> getUsers() {
		return users;
	}
	public void setUsers(java.util.Set<User> users) {
		this.users = users;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String[] getGates() {
		return gates;
	}
	public void setGates(String[] gates) {
		this.gates = gates;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTicketSequence() {
		return ticketSequence;
	}
	public void setTicketSequence(String ticketSequence) {
		this.ticketSequence = ticketSequence;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public boolean isReturnable() {
		return returnable;
	}
	public void setReturnable(boolean returnable) {
		this.returnable = returnable;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
