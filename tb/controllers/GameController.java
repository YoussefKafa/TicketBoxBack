package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.tb.dao.GameRepo;
import com.project.tb.models.Game;
import com.project.tb.models.Stadium;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
import com.project.tb.services.GameServices;
@RestController
@RequestMapping("/api/game")
class GameController{
@Autowired
private MapValidationErrorService mapvalidationErrorService;
@Autowired
private GameServices gameService;
@Autowired
private GameRepo gameRepo;
@PostMapping("/save")
public ResponseEntity<?> addGame(@Valid @RequestBody Game game, BindingResult result){
      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
      if (errorMap!=null) return errorMap;
    Game game2=gameService.saveOrUpdate(game);
return new ResponseEntity<Game>(game,HttpStatus.CREATED);
}
@PutMapping("/save")
public void updateGame(@Valid @RequestBody Game game, BindingResult result){
    Game game2=gameService.saveOrUpdate(game);
}
@GetMapping("/findAll")
public List<Game> allGames() {
	return gameService.findAll();
}
@GetMapping("/count")
public Long count() {
    return gameService.count();
}
@DeleteMapping("/deleteById/{id}")
public void deleteById(@PathVariable Long id) {
	gameService.deleteById(id);
}
@GetMapping("/findById/{id}")
public Optional<Game> findByName(@PathVariable Long id) {
    return gameService.findById(id);
}
@PostMapping("/addTeam/{gameId}/{teamId}")
public void addTeam(@PathVariable Long gameId, @PathVariable Long teamId) {
	 gameService.addTeam(gameId, teamId);
}
@PostMapping("/addStadium/{gameId}/{stadiumId}")
public ResponseEntity<?> addStadium(@PathVariable Long gameId,@PathVariable Long stadiumId) {
	 gameService.addStadium(gameId,stadiumId);
	 Optional<Game> game=gameService.findById(gameId);
	 return new ResponseEntity<Game>(game.get(),HttpStatus.CREATED);
}
@DeleteMapping("/deleteTeams/{gameId}")
public void deleteTeams(@PathVariable Long gameId) {
	gameService.deleteTeams(gameId);
}
@DeleteMapping("/deleteTeam/{gameId}/{teamId}")
public void deleteTeam(@PathVariable Long gameId,@PathVariable Long teamId) {
	 gameService.deleteTeam(gameId,teamId);
}
}