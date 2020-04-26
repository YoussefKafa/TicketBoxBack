package com.project.tb.models;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Team extends AuditModel implements Serializable{
	@Transient
	private String defaultCountryString  = "Unknown";
@Id
@GeneratedValue
@Column(name = "TEAM_ID", unique = true, nullable = false)
private Long id;
private String name;
private String city;
private String country=defaultCountryString;
@Lob
@Column(name="image", nullable=false, columnDefinition="mediumblob") //medium is 16mb
private String image;
/////////////////////////
///////////////////////////
public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}
	/* getters
    and
  setters */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
   
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}