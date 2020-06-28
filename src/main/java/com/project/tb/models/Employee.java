package com.project.tb.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "Employee")
public class Employee extends DateAudit{
    @Id
    @GeneratedValue
    private  Long id;
    @NotBlank(message = "Please enter employee full name")
    @Email(message = "Please enter a valid email")
    @NotBlank(message = "email is required")
    @Column(unique = true)
    private String email;
    private  String password;
    @Enumerated(EnumType.STRING)
    private EmployeeType EmployeeType;
    private String phone;
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    public EmployeeType getEmployeeType() {
		return EmployeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		EmployeeType = employeeType;
	}
	public Long getEmployeeId() {
        return this.id;
    }
    public void setEmployeeId(Long employeeId) {
        this.id = employeeId;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}