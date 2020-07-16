package com.project.tb.payload;
public class ChangePasswordRequest {
private String idOfUser;
private String oldPass;
private String newPass;

public String getOldPass() {
	return oldPass;
}
public void setOldPass(String oldPass) {
	this.oldPass = oldPass;
}
public String getNewPass() {
	return newPass;
}
public void setNewPass(String newPass) {
	this.newPass = newPass;
}
public String getIdOfUser() {
	return idOfUser;
}
public void setIdOfUser(String idOfUser) {
	this.idOfUser = idOfUser;
}


}
