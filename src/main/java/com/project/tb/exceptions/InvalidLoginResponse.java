package com.project.tb.exceptions;
public class InvalidLoginResponse{
    private String name;
    private String password;
    public InvalidLoginResponse(){
        //we put the name and password as invalid so we dont give the hacker a hint
        this.name="invalid name";
        this.password="invalid password";
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