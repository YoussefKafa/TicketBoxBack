package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import com.project.tb.models.Ticket;
import com.project.tb.services.TicketServices;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/ticket")
class TicketController{
@Autowired
private TicketServices ticketService;
@Autowired
private MapValidationErrorService mapvalidationErrorService;
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/save")
public ResponseEntity<?> save(@Valid @RequestBody Ticket ticket, BindingResult result){
      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
      if (errorMap!=null) return errorMap;
    Ticket ticket1=ticketService.saveOrUpdate(ticket);
return new ResponseEntity<Ticket>(ticket,HttpStatus.CREATED);
}
@GetMapping("/show/findAll")
public List<Ticket> allTickets() {
	return ticketService.findAll();
}
@GetMapping("/findById/{id}")
public Ticket findById(@PathVariable String id){
	return ticketService.findById(Long.parseLong(id)).get();
}
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/count")
public Long count() {
    return ticketService.count();
}
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/deleteAll")
public void deleteAll() {
	ticketService.deleteAll();
}
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/deleteById/{id}")
public void deleteById(@PathVariable Long id) {
	ticketService.deleteById(id);
}
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/addGame/{ticket_id}/{game_id}")
public ResponseEntity<?> addGame(@PathVariable Long ticket_id,@PathVariable Long game_id) {
	 ticketService.addGame(ticket_id,game_id);
	 Optional<Ticket> ticket=ticketService.findById(ticket_id);
	 return new ResponseEntity<Ticket>(ticket.get(),HttpStatus.CREATED);
}
}