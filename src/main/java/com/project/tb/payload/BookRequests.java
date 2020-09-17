package com.project.tb.payload;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.tb.models.DateAudit;
import com.project.tb.models.QRCode;
import com.project.tb.security.AES;
import com.project.tb.security.SecurityConstants;
@Entity
public class BookRequests extends DateAudit {
	public BookRequests() {
	}
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "ticket_id")
	private Long ticketId;
	@Column(name = "email")
	@JsonIgnore
	private String email;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bookRequest")
	@JsonIgnore
	private QRCode qrCode;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public QRCode getQrCode() {
		return qrCode;
	}
	public void setQrCode(QRCode qrCode) {
		this.qrCode = qrCode;
	}
}
