package com.project.tb.models;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.tb.payload.CreditRequest;
//ManyToMany with Tickets
//OneToOne with TicketsList
@Entity
//user details take care of user security and password	// things
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
	private String email;
	private int age;
	private boolean gender;
	 @NotBlank
	    @Size(max = 100)
	private String password;
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_roles",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();
	private boolean enabled=true;
	public User() {

    }
	  public User(String name, String email, String password,int age,boolean gender) {
	        this.name = name;
	        this.email = email;
	        this.password = password;
	        this.age=age;
	        this.gender=gender;
	    }
	@ManyToMany(mappedBy = "users")
	@JsonIgnore
	private java.util.Set<Ticket> tickets = new HashSet<>();
	private int credit=0;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private List<CreditRequest> creditRequest = new ArrayList<>();
	///////////////////////////////////////////
	///////////////////////////////////////////
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public java.util.Set<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(java.util.Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int cash) {
		this.credit = cash;
	}

	////////////////////////////////////
	////////////////////////////////////
	
}