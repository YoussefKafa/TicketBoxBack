package com.project.tb.models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
//new class


@Entity
public class GameTeams implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
private int guest;
private int host;

@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "game_id", nullable = true)
@JsonIgnore
private Game game;
public GameTeams() {
	
}
public Game getGame() {
	return game;
}
public void setGame(Game game) {
	this.game = game;
}
public int getGuest() {
	return guest;
}
public void setGuest(int guest) {
	this.guest = guest;
}
public int getHost() {
	return host;
}
public void setHost(int host) {
	this.host = host;
}
}
