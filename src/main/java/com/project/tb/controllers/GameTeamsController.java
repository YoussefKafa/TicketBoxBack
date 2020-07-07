package com.project.tb.controllers;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.tb.dao.GameRepo;
import com.project.tb.dao.GameTeamsRepo;
import com.project.tb.models.Game;
import com.project.tb.models.GameTeams;
import com.project.tb.models.Team;
import com.project.tb.services.GameServices;
import com.project.tb.services.GameTeamsServices;
import com.project.tb.services.MapValidationErrorService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/gameTeams")
public class GameTeamsController {
	@Autowired
	private GameTeamsServices gameTeamsServices;
	@Autowired
	private GameTeamsRepo gameTeamsRepo;
	@Autowired
	private GameRepo gameRepo;
	@Autowired
	private MapValidationErrorService mapvalidationErrorService;
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody GameTeams gameTeams, BindingResult result){
	      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
	      if (errorMap!=null) return errorMap;
	    GameTeams team1=gameTeamsServices.saveOrUpdate(gameTeams);
	return new ResponseEntity<GameTeams>(gameTeams,HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/insertTeam/{guestId}/{hostId}/{game_id}")
	public ResponseEntity<?>  insertTeam(@PathVariable int guestId,@PathVariable int hostId,@PathVariable Long game_id ){
	     gameTeamsRepo.addGameTeam(guestId, hostId, game_id);
	     Optional<Game> game2=gameRepo.findById(game_id);
	     return new ResponseEntity<Game>(game2.get(),HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateTeams/{hostId}/{guestId}/{game_id}")
	public ResponseEntity<?>  updateTeams(@PathVariable int guestId,@PathVariable int hostId,@PathVariable int game_id )
	{
		 gameTeamsRepo.updateGameTeamsByGameId(hostId, guestId, game_id);
	     Optional<Game> game2=gameRepo.findById((long)game_id);
	     return new ResponseEntity<Game>(game2.get(),HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/show/findAll")
	public List<GameTeams> allGameTeams() {
		return gameTeamsServices.findAll();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/show/findByGameId/{game_id}")
	public ResponseEntity<?>  findByGameId(@PathVariable Long game_id) {
	 Optional<GameTeams> gameTeams= gameTeamsServices.findByGameId(game_id);
		return new ResponseEntity<GameTeams>(gameTeams.get(),HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteTeams/{game_id}")
	public ResponseEntity<?> deleteGameTeams(@PathVariable Long game_id) {
		 gameTeamsServices.deleteTeams(game_id);
		 Optional<Game> game2=gameRepo.findById(game_id);
	     return new ResponseEntity<Game>(game2.get(),HttpStatus.CREATED);
	}
}
