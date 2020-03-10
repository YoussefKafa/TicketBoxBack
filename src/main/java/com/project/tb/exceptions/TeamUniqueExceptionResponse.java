package com.project.tb.exceptions;
public class TeamUniqueExceptionResponse{
    private String name;
    public TeamUniqueExceptionResponse(String name) {
        this.name=name;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}