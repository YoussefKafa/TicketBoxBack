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
import com.project.tb.models.Ticket;
import com.project.tb.services.TicketServices;
import java.util.List;
import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
@RestController
@RequestMapping("/api/ticket")
class TicketController{
@Autowired
private TicketServices ticketService;
@Autowired
private MapValidationErrorService mapvalidationErrorService;
@PostMapping("/save")
public ResponseEntity<?> save(@Valid @RequestBody Ticket ticket, BindingResult result){
      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
      if (errorMap!=null) return errorMap;
    Ticket ticket1=ticketService.saveOrUpdate(ticket);
return new ResponseEntity<Ticket>(ticket,HttpStatus.CREATED);
}
@GetMapping("/findAll")
public List<Ticket> allTickets() {
	return ticketService.findAll();
}
@GetMapping("/count")
public Long count() {
    return ticketService.count();
}
@DeleteMapping("/deleteById/{id}")
public void deleteById(@PathVariable Long id) {
	ticketService.deleteById(id);
}
}