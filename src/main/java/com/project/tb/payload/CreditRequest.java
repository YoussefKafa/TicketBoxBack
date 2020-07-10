package com.project.tb.payload;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.project.tb.models.DateAudit;
import com.project.tb.models.User;
@Entity
public class CreditRequest extends DateAudit implements Serializable {
	 @Id
	    @GeneratedValue
	    private  Long id;
	 private Long distuId;
private int credit;
private String email;
@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
@JoinColumn(name = "user_id", updatable = true, nullable = true)
private User user;
public int getCredit() {
	return credit;
}
public void setCredit(int credit) {
	this.credit = credit;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Long getDistuId() {
	return distuId;
}
public void setDistuId(Long distuId) {
	this.distuId = distuId;
}

}
