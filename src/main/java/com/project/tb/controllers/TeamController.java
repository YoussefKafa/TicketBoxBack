package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import com.project.tb.models.Team;
import com.project.tb.services.TeamServices;
import java.util.List;
import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
@RestController
@RequestMapping("/api/team")
class TeamController{
@Autowired
private TeamServices teamService;
@Autowired
private MapValidationErrorService mapvalidationErrorService;
@PostMapping("/save")
public ResponseEntity<?> save(@Valid @RequestBody Team team, BindingResult result){
      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
      if (errorMap!=null) return errorMap;
    Team team1=teamService.saveOrUpdate(team);
return new ResponseEntity<Team>(team,HttpStatus.CREATED);
}
@GetMapping("/findAll")
public List<Team> allTeams() {
	return teamService.findAll();
}
@GetMapping("/count")
public Long count() {
    return teamService.count();
}
@DeleteMapping("/deleteById/{id}")
public void deleteById(@PathVariable Long id) {
	teamService.deleteById(id);
}
}