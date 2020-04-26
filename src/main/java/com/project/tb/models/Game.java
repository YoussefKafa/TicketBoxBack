package com.project.tb.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="game", uniqueConstraints = {
		@UniqueConstraint(columnNames = "gameIdentifier")
})
public class Game extends AuditModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_id",unique = true, nullable = false)
	private Long id;
	private String gameIdentifier;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_stadium", nullable = true, updatable = true)
	//@JsonIgnore
	private Stadium stadium;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "game")
	private List<Ticket> tickets = new ArrayList<>();
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
	private GameTeams gameTeams;
	///////////////
///////////
	public GameTeams getGameTeams() {
		return gameTeams;
	}

	public void setGameTeams(GameTeams gameTeams) {
		this.gameTeams = gameTeams;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", gameIdentifier=" + gameIdentifier + " stadium=" + stadium
				+ ", tickets=" + tickets + ", deadLine=" + deadLine + "]";
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}
	public String getGameIdentifier() {
		return gameIdentifier;
	}

	public void setGameIdentifier(String gameIdentifier) {
		this.gameIdentifier = gameIdentifier;
	}
	private  String deadLine;

	public String getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(String deadLine) {
		this.deadLine = deadLine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
