package com.project.tb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import com.project.tb.models.Team;
import com.project.tb.services.TeamServices;
import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
@RestController
@RequestMapping("/api/team")
class TeamController{
@Autowired
private TeamServices teamService;
@Autowired
private MapValidationErrorService mapvalidationErrorService;
@PostMapping("")
public ResponseEntity<?> addTeam(@Valid @RequestBody Team team, BindingResult result){
      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
      if (errorMap!=null) return errorMap;
    Team team1=teamService.saveOrUpdate(team);
return new ResponseEntity<Team>(team,HttpStatus.CREATED);
}
}