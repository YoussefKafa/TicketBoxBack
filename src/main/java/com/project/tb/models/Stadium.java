package com.project.tb.models;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public
class Stadium extends AuditModel{
    @Id
    @GeneratedValue
    private  Long stadiumId;
@NotBlank(message = "Name cannot be blank")
private String name;
@NotBlank(message = "city cannot be blank: team city is required")
private String city;
@Lob
@Column(name="image", nullable=true, columnDefinition="mediumblob") //medium is 16mb
private String image;
@OneToMany(mappedBy = "stadium")
private List<Game> games = new ArrayList<Game>();
///////////////////////
///////////////////////
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