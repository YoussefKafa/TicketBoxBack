package com.project.tb.models;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
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