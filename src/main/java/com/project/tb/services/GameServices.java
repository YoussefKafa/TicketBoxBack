package com.project.tb.services;
import java.util.ArrayList;
import java.util.List;
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
            return gameRepo.save(game);
        } catch (final Exception e) {
            throw new GameUniqueException("Game  "+ game.getId()+ " is already exists");
        }
        
    }
    public List<Game> findAll() {
        var it = gameRepo.findAll();
        var games = new ArrayList<Game>();
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
    public void deleteAll(Game game) {
        gameRepo.delete(game);
     }
}