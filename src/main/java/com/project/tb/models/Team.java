package com.project.tb.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.validation.constraints.*;
@Entity
public class Team{
@Id
@GeneratedValue
Long id;
@NotBlank(message = "Name cannot be blank")
@Size(min=2,max=5,message="name must be between 2 to 5 characters")
String name;
@NotBlank(message = "city cannot be blank: team city is required")
String city;
@Lob
@Column(name="image", nullable=false, columnDefinition="mediumblob") //medium is 16mb
private byte[] image;
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