package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.project.tb.models.Stadium;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
import com.project.tb.services.StadiumServices;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/stadium")
class StadiumController{
@Autowired
private MapValidationErrorService mapvalidationErrorService;
@Autowired
private StadiumServices stadiumService;
@PostMapping("/save")
public ResponseEntity<?> addStadium(@Valid @RequestBody Stadium stadium, BindingResult result){
      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
      if (errorMap!=null) return errorMap;
    Stadium stadium2=stadiumService.saveOrUpdate(stadium);
return new ResponseEntity<Stadium>(stadium,HttpStatus.CREATED);
}

@GetMapping("/show/findAll")
public List<Stadium> allStadiums() {
	return stadiumService.findAll();
}
@GetMapping("/count")
public Long count() {
    return stadiumService.count();
}
//if necessary, team can use it
@DeleteMapping("/deleteByName/{name}")
public void deleteByName(@PathVariable String name) {
	stadiumService.deleteByName(name);
}
@DeleteMapping("/deleteById/{id}")
public void deleteById(@PathVariable Long id) {
	stadiumService.deleteById(id);
}
@GetMapping("/getIdByName/{name}")
public String getIdByName(@PathVariable String name) {
    return stadiumService.getIdByName(name);
}

@GetMapping("/findByName/{name}")
public Optional<Stadium> findByName(@PathVariable String name) {
    return stadiumService.findByName(name);
}
@GetMapping("/show/findById/{id}")
public Optional<Stadium> findByName(@PathVariable Long id) {
    return stadiumService.findById(id);
}
}