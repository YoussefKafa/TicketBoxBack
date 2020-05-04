package com.project.tb.models;

import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

//ManyToMany with Tickets
//OneToOne with TicketsList
@Entity
//user details take care of user security and password	// things
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
public class User extends AuditModel implements UserDetails{ 
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
    	this.updatedAt=new Date();
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
	public Date setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
		return updatedAt;
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
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}