package com.project.tb.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.project.tb.payload.BookRequests;
import com.project.tb.services.BookRequestsService;
import com.project.tb.services.TicketServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bookRequests")
public class BookRequestsController {
	@Autowired
	private BookRequestsService bookRequestsService;
	
	@Autowired
	private TicketServices ticketServices;
	@RequestMapping(value="/myreservations/{email}", method=RequestMethod.GET)
	public List<BookRequests> myReservations(@PathVariable String email){
		return bookRequestsService.findByEmail(email);
	}
	@RequestMapping(value="/book", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void book(@RequestBody BookRequests bookRequest) throws WriterException, IOException{
		bookRequestsService.save(bookRequest);
	}
}
