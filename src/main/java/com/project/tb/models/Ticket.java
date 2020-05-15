package com.project.tb.models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

//many to one with TicketsList

@Entity
public class Ticket extends AuditModel {
	@Id
	@GeneratedValue
	private Long id;
	@Column(updatable = false)
	// we are going to use it to find an individual ticket in the TicketsList
	private String ticketSequence;
	private int price;
	private String section;
	private boolean returnable;
	private int gate;
	private Date releaseDate;
	private Date endDate;
	private Date returnDate;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "game_id", updatable = false, nullable = false)
	@JsonIgnore
	private Game game;
	////////////////////////////////
	///////////////////////////////
	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getGate() {
		return gate;
	}

	public void setGate(int gate) {
		this.gate = gate;
	}
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
