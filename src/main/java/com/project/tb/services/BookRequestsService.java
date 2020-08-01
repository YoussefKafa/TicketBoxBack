package com.project.tb.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.tb.dao.BookRequestRepo;
import com.project.tb.dao.GameRepo;
import com.project.tb.dao.TicketRepo;
import com.project.tb.dao.UserRepo;
import com.project.tb.models.Ticket;
import com.project.tb.models.User;

import java.util.List;
import java.util.Optional;

import com.project.tb.payload.BookRequests;
public class BookRequestsService {
	@Autowired
	BookRequestRepo bookRequestRepo;
	@Autowired
	TicketRepo ticketRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	GameRepo gameRepo;
	public List<BookRequests> findByEmail(String email)
	{
		return bookRequestRepo.findByEmail(email);
	}
	public void save(BookRequests bookRequest) {
		ticketRepo.decreaseCounter(bookRequest.getTicketId());
		Optional<Ticket> tikTicket=ticketRepo.findById(bookRequest.getTicketId());
		int price=tikTicket.get().getPrice();
		userRepo.decreaseCredit(price, userRepo.findByEmail(bookRequest.getEmail()).getId());
		gameRepo.increaseSaleCounter(tikTicket.get().getGame().getId());
		bookRequestRepo.save(bookRequest);
	}
	public void delete(Long id) {
		bookRequestRepo.deleteById(id);
	}
}
