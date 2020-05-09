package com.project.tb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.GameTeamsRepo;
import com.project.tb.exceptions.TeamUniqueException;
import com.project.tb.models.GameTeams;
import com.project.tb.models.Team;
import com.project.tb.models.User;

@Service
public class GameTeamsServices {
    @Autowired
    private GameTeamsRepo gameTeamsRepo;
    public GameTeams saveOrUpdate(final GameTeams gameTeams) {
            return gameTeamsRepo.save(gameTeams);
        }
    public void insertTeam(int guestId, int hostId, long game_id) {
    	gameTeamsRepo.addGameTeam(guestId, hostId, game_id);
    }
    public void deleteTeams( long game_id) {
    	gameTeamsRepo.deleteGameTeams(game_id);
    }
    public void updateGameTeamsByGameId(int host, int guest, int gameId) {
    	gameTeamsRepo.updateGameTeamsByGameId(host, guest, gameId);
    }
    public List<GameTeams> findAll() {
		Iterable<GameTeams> it = gameTeamsRepo.findAll();
		ArrayList<GameTeams> gameTeams = new ArrayList<GameTeams>();
		it.forEach(e -> gameTeams.add(e));
		return gameTeams;
	}
	public Optional<GameTeams> findByGameId(Long game_id) {
		return gameTeamsRepo.findById(game_id);
	}
}
