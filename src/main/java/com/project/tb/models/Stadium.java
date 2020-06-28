package com.project.tb.models;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        }
)
public
class Stadium extends DateAudit{
    @Id
    @GeneratedValue
    @Basic(fetch = FetchType.LAZY)
    private  Long stadiumId;
    @Transient
    Date defaultDate = new Date(2020, 20, 20); 
@NotBlank(message = "Name cannot be blank")
private String name;
@NotBlank(message = "city cannot be blank: team city is required")
private String city;
@Lob
@Column(name="image", nullable=true, columnDefinition="mediumblob") //medium is 16mb
private String image="0";
@OneToMany(mappedBy = "stadium")
@JsonIgnore
private List<Game> games = new ArrayList<Game>();
@Column(nullable = true, updatable = true, unique = false)
private int capacity=0;
private int gates;
///////////////////////
///////////////////////
public int getGates() {
	return gates;
}
public void setGates(int gates) {
	this.gates = gates;
}
public int getCapacity() {
	return capacity;
}
public void setCapacity(int capacity) {
	this.capacity = capacity;
}
public List<Game> getGames() {
	return games;
}

public void setGames(List<Game> games) {
	this.games = games;
}

public Long getStadiumId() {
	return stadiumId;
}

public void setStadiumId(Long stadiumId) {
	this.stadiumId = stadiumId;
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