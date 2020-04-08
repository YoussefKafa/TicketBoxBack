package com.project.tb.models;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Team extends AuditModel implements Serializable{
@Id
@GeneratedValue
@Column(name = "TEAM_ID", unique = true, nullable = false)
private Long id;
private String name;
private String city;
@Lob
@Column(name="image", nullable=false, columnDefinition="mediumblob") //medium is 16mb
private byte[] image;
@ManyToMany(fetch = FetchType.LAZY, mappedBy = "teams")
@JsonIgnore
private Set<Game> games = new HashSet<Game>(0);

	public Set<Game> getGames() {
	return games;
}

public void setGames(Set<Game> games) {
	this.games = games;
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
   
    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}