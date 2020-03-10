package com.project.tb.models;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "Employee")
public class Employee extends AuditModel{
    @Id
    @GeneratedValue
    private  Long id;
    @NotBlank(message = "Please enter employee full name")
    private  String name;
    @NotBlank(message = "Please enter employee password")
    private  String password;
    @Enumerated(EnumType.STRING)
    private EmployeeType EmployeeType;
    private String phone;
    private String email;
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}