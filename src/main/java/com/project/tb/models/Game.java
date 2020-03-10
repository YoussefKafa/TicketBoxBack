package com.project.tb.models;

import java.util.Date;

public class Game {
private Long id;
private Team team1;
private Team team2;
private Date createdAt;
private Date updatedAt;
private Stadium stadium;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Team getTeam1() {
	return team1;
}
public void setTeam1(Team team1) {
	this.team1 = team1;
}
public Team getTeam2() {
	return team2;
}
public void setTeam2(Team team2) {
	this.team2 = team2;
}
public Date getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}
public Date getUpdatedAt() {
	return updatedAt;
}
public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}
public Stadium getStadium() {
	return stadium;
}
public void setStadium(Stadium stadium) {
	this.stadium = stadium;
}
}
