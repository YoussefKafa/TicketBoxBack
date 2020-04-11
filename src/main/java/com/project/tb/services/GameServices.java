package com.project.tb.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.models.*;
import com.project.tb.exceptions.GameUniqueException;
@Service
public class GameServices{
    @Autowired
    private GameRepo gameRepo;
    public Game saveOrUpdate(final Game game) {
        try {
        	if(game.getId()==null)
                return gameRepo.save(game);
            	else {
            	Optional<Game> newgame=gameRepo.findById(game.getId());
            	game.setTeams(newgame.get().getTeams());
            	return gameRepo.save(game);
            		}
        } catch (final Exception e) {
            throw new GameUniqueException(e.getMessage());
        }
        
    }
    public List<Game> findAll() {
        Iterable<Game> it = gameRepo.findAll();
        ArrayList<Game> games = new ArrayList<Game>();
        it.forEach(e -> games.add(e));
        return games;
    }
    public Long count() {
        return gameRepo.count();
    }
    public void deleteById(Long gameId) {
       gameRepo.deleteById(gameId);
    }
    public void deleteAll() {
        gameRepo.deleteAll();
     }
	public Optional<Game> findById(Long id) {
		return gameRepo.findById(id);
	}
}