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
	@Column(name = "GAME_ID", unique = true, nullable = false)
	private Long id;
	private String gameIdentifier;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "game_teams", joinColumns = { 
			@JoinColumn(name = "GAME_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "TEAM_ID", 
					nullable = false, updatable = false) })
	private Set<Team> teams = new HashSet<Team>(0);
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_stadium", nullable = false, updatable = false)
	@JsonIgnore
	private Stadium stadium;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "game")
	private List<Ticket> tickets = new ArrayList<>();
	///////////////
///////////

	public List<Ticket> getTickets() {
		return tickets;
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

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public String getGameIdentifier() {
		return gameIdentifier;
	}

	public void setGameIdentifier(String gameIdentifier) {
		this.gameIdentifier = gameIdentifier;
	}
	private Date deadLine;

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
