package com.project.tb.payload;
public class ChangeForgPassword {
private String email;
private String newPassword;
private int code;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getNewPassword() {
	return newPassword;
}
public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}

}
