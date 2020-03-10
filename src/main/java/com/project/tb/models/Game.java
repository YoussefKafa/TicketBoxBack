package com.project.tb.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity(name="Game")
@Table(name="game")
public class Game extends AuditModel{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long gameId;
@OneToMany(
		mappedBy = "game",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
private Collection<Team> teams=new ArrayList<>();
@OneToOne
@JoinColumn(name="stadiumId")
private Stadium stadium;
public Stadium getStadium() {
	return stadium;
}
public void setStadium(Stadium stadium) {
	this.stadium = stadium;
}
private Date deadLine;
public Date getDeadLine() {
	return deadLine;
}
public void setDeadLine(Date deadLine) {
	this.deadLine = deadLine;
}
public Long getId() {
	return gameId;
}
public void setId(Long id) {
	this.gameId = id;
}
public Collection<Team> getTeams() {
	return teams;
}
public void setTeams(Collection<Team> teams) {
	this.teams = teams;
}
}
