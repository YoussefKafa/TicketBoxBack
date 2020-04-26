package com.project.tb.exceptions;
public class InvalidLoginResponse{
    private String email;
    private String password;
    public InvalidLoginResponse(){
        //we put the email and password as invalid so we dont give the hacker a hint
        this.email="Invalid email";
        this.password="Invalid Password";
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}