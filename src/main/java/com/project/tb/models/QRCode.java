package com.project.tb.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.tb.payload.BookRequests;

@Entity
public class QRCode {
	public QRCode() {
		
	}
	public QRCode( String email, String ticketSequence, String image, BookRequests bookRequest) {
		this.email = email;
		this.ticketSequence = ticketSequence;
		this.image = image;
		this.bookRequest = bookRequest;
	}
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String ticketSequence;
	@Lob
	@Column(name="image", nullable=true, columnDefinition="mediumblob")//medium is 16mb
	private String image;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", nullable = false)
	@JsonIgnore
	private BookRequests bookRequest;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTicketSequence() {
		return ticketSequence;
	}
	public void setTicketSequence(String ticketSequence) {
		this.ticketSequence = ticketSequence;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public BookRequests getBookRequest() {
		return bookRequest;
	}
	public void setBookRequest(BookRequests bookRequest) {
		this.bookRequest = bookRequest;
	}

}
