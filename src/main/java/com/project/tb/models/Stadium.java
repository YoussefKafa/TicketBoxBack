package com.project.tb.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
@Entity
class Stadium {
    @Id
    @GeneratedValue
    Long stadiumID;
@NotBlank(message = "Name cannot be blank")
@Size(min=2,max=5,message="name must be between 2 to 5 characters")
String name;
@NotBlank(message = "city cannot be blank: team city is required")
String city;
@Lob
@Column(name="image", nullable=false, columnDefinition="mediumblob") //medium is 16mb
private byte[] image;

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