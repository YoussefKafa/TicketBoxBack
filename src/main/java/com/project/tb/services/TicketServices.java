package com.project.tb.services;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tb.dao.BookRequestRepo;
import com.project.tb.dao.TicketRepo;
import com.project.tb.dao.UserRepo;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.Ticket;
import com.project.tb.payload.ReturnTicketRequest;
@Service
public class TicketServices {
	 @Autowired
	    private TicketRepo ticketRepo;
	 @Autowired
	    private UserRepo userRepo;
	 @Autowired
	 private BookRequestRepo bookRequestRepo;
		public Ticket saveOrUpdate(final Ticket ticket) {
	        try {
	        if(ticket.getId()==null) {
	            return ticketRepo.save(ticket);}
	        else {
	        	Optional<Ticket> ticket2=ticketRepo.findById(ticket.getId());
	        	Ticket ticket22=ticket2.get();
	        	ticket22.setCounter(ticket.getCounter());
	        	ticket22.setEndDate(ticket.getEndDate());
	        	ticket22.setGates(ticket.getGates());
	        	ticket22.setPrice(ticket.getPrice());
	        	ticket22.setReleaseDate(ticket.getReleaseDate());
	        	ticket22.setReturnDate(ticket.getReturnDate());
	        	ticket22.setReturnable(ticket.isReturnable());
	        	ticket22.setTicketSequence(ticket.getTicketSequence());
	        	return ticketRepo.save(ticket22);
	        }
	        } catch (final Exception e) {
	            throw new ModelException("Ticket  "+ ticket.getId()+ " is already exists"+e.getMessage());
	        } 
	    }
	    public List<Ticket> findAll() {
	        Iterable<Ticket> it = ticketRepo.findAll();
	        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	        it.forEach(e -> tickets.add(e));
	        return tickets;
	    }
	    public Long count() {
	        return ticketRepo.count();
	    }
	    public void deleteById(Long ticketId) {
	       ticketRepo.deleteTicket(ticketId);
	    }
	    public void deleteAll() {
	        ticketRepo.deleteAll();
	     }
	    public void deleteAll(Ticket ticket) {
	        ticketRepo.delete(ticket);
	     }
	    public TicketRepo getTicketRepo() {
			return ticketRepo;
		}
		public void setTicketRepo(TicketRepo ticketRepo) {
			this.ticketRepo = ticketRepo;
		}
		public void addGame(Long ticket_id,Long game_id) {
			ticketRepo.addGame(ticket_id,game_id);
		}
		public Optional<Ticket> findById(Long id) {
			return ticketRepo.findById(id);
		}
		public void decreaseCounter(Long ticket_id) {
			ticketRepo.decreaseCounter(ticket_id);
		}
	public boolean existsByTicketSequence(String ticketSequence) {
		return ticketRepo.existsByTicketSequence(ticketSequence);
	}	
	public boolean returnTicket(ReturnTicketRequest returnTicketRequest) {
		boolean result=false;
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);
	    Long returnDay=Long.parseLong(returnTicketRequest.getReturnDate().substring(0, returnTicketRequest.getReturnDate().indexOf("/")));
	    System.out.println(returnDay);
	    Long nowDay=Long.parseLong(strDate.substring(0, strDate.indexOf("/")));
	    System.out.println(nowDay);
	    System.out.println(returnTicketRequest.getReturnDate());
	    System.out.println(returnTicketRequest.getReturnDate().substring(3,6));
	  Long returnMonth=Long.parseLong(returnTicketRequest.getReturnDate().substring(3,5));
	  System.out.println(returnMonth);
	  Long nowMonth=Long.parseLong(strDate.substring(3,5));
	  System.out.println(nowMonth);
	  if(returnMonth>=nowMonth ) { result=true;
		ticketRepo.increaseCounter(Long.parseLong(returnTicketRequest.getTicketId()));
		userRepo.increaseCredit(returnTicketRequest.getPrice(), Long.parseLong(returnTicketRequest.getUserId()));
		bookRequestRepo.deleteById(Long.parseLong(returnTicketRequest.getBrId()));
		return true;
	  }
	  if(returnMonth==nowMonth ) { 
		  if(returnDay>=nowDay) {result=true;
		ticketRepo.increaseCounter(Long.parseLong(returnTicketRequest.getTicketId()));
		userRepo.increaseCredit(returnTicketRequest.getPrice(), Long.parseLong(returnTicketRequest.getUserId()));
		bookRequestRepo.deleteById(Long.parseLong(returnTicketRequest.getBrId()));
		return true;}
		  if(returnMonth<nowMonth ) { 
			  result=false;
			 return false;}
		  
	  }
		return result;
	}
}
	
