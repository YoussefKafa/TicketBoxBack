package com.project.tb.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

//OneToOne with the User
@Entity
public class TicketsList {
	// one to one with the User
	// one to many with the ticket
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String userIdentifier;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;
	// one to many with Ticket
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tickets")
	private List<Ticket> userTickets = new ArrayList<>();

	//////////////////////////////////
	///////////////////////////////
	public List<Ticket> getUserTickets() {
		return userTickets;
	}

	public void setUserTickets(List<Ticket> userTickets) {
		this.userTickets = userTickets;
	}

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TicketsList() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
