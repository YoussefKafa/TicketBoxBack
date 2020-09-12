package com.project.tb.services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.*;
@Service
public class GameServices{
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private StadiumRepo stadiumRepo;
    public Game saveOrUpdate(final Game game) {
        try {
        	if(game.getId()==null)
        return	gameRepo.save(game);
        	else  {
				Optional<Game> game1=findById(game.getId());
				Game game2=game1.get();
			game.setStadium(game2.getStadium());
			game.setGameIdentifier(game2.getGameIdentifier());
			Date updateDate=new Date();
			return gameRepo.save(game);
			}
        } catch (final Exception e) {
            throw new ModelException(e.getMessage());
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
	public void addTeam(Long gameId, Long teamId) {
		gameRepo.addTeam(gameId, teamId);
	}
	public void addStadium(Long gameId,Long stadiumId) {
		gameRepo.addStadium(gameId,stadiumId);
	}
	public void deleteTeams(Long gameId) {
		gameRepo.deleteTeams(gameId);
		
	}
	public void deleteTeam(Long gameId, Long teamId) {
		gameRepo.deleteTeam(gameId,teamId);
		
	}
	public int salesCount() {
		return gameRepo.salesCount();
	}
}