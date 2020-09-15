package com.project.tb.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.zxing.WriterException;
import com.project.tb.dao.BookRequestRepo;
import com.project.tb.dao.GameRepo;
import com.project.tb.dao.TicketRepo;
import com.project.tb.dao.UserRepo;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.Ticket;
import com.project.tb.models.User;

import java.io.IOException;
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
	public void save(BookRequests bookRequest) throws WriterException, IOException {
	
		Optional<Ticket> tikTicket=ticketRepo.findById(bookRequest.getTicketId());
		int price=tikTicket.get().getPrice();
		User user=userRepo.findByEmail(bookRequest.getEmail());
		int credit=user.getCredit();
		if(credit>=price) {
		userRepo.decreaseCredit(price, userRepo.findByEmail(bookRequest.getEmail()).getId());}
		else {
			throw new ModelException("Dear User: You don't have enough credit!");
		}
		gameRepo.increaseSaleCounter(tikTicket.get().getGame().getId());
		ticketRepo.decreaseCounter(bookRequest.getTicketId());
		QRCodeServices.createQRCode(user.getName()+"$"+tikTicket.get().getTicketSequence(), user.getEmail()+"#"+tikTicket.get().getTicketSequence());
		bookRequestRepo.save(bookRequest);
	}
	public void delete(Long id) {
		bookRequestRepo.deleteById(id);
	}
}
