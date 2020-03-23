package com.project.tb.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//ManyToMany with Tickets
//OneToOne with TicketsList
@Entity
//user details take care of user security and password														// things
public class User extends AuditModel{ 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String userIdentifier;
	private String name;
	private String email;
	private String password;
	@Transient // make sure it matches with password before persiste our user
	private String confirmPassword;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	private TicketsList ticketsList;
	private Date createdAt;
	private Date updatedAt;
    @PrePersist
    protected void onCreate() {
    	this.createdAt=new Date();
    }
    @PreUpdate
    protected void OnUpdate() {
    	this.updatedAt=new Date();
    }
    ////////////////////////////////////
    ////////////////////////////////////
    public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public TicketsList getTicketsList() {
		return ticketsList;
	}

	public void setTicketsList(TicketsList ticketsList) {
		this.ticketsList = ticketsList;
	}

	public Long getUserId() {
		return this.id;
	}

	public void setUserId(Long userId) {
		this.id = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}