package com.project.tb.models;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.validation.constraints.*;
@Entity
public class Team extends AuditModel{
@Id
@GeneratedValue
private Long teamId;
@NotBlank(message = "Name cannot be blank")
@Size(min=2,max=5,message="name must be between 2 to 5 characters")
private String name;
@NotBlank(message = "city cannot be blank: team city is required")
private String city;
@Lob
@Column(name="image", nullable=false, columnDefinition="mediumblob") //medium is 16mb
private byte[] image;
@ManyToOne(fetch = FetchType.LAZY)
private Game game;
public Game getGame() {
	return game;
}

public void setGame(Game game) {
	this.game = game;
}

	/* getters
    and
  setters */
    public Long getId() {
        return this.teamId;
    }

    public void setId(Long id) {
        this.teamId = id;
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