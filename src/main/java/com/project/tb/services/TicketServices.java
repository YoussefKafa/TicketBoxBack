package com.project.tb.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.TicketRepo;
import com.project.tb.exceptions.TicketUniqueException;
import com.project.tb.models.Ticket;
@Service
public class TicketServices {
	 @Autowired
	    private TicketRepo ticketRepo;
		public Ticket saveOrUpdate(final Ticket ticket) {
	        try {
	            return ticketRepo.save(ticket);
	        } catch (final Exception e) {
	            throw new TicketUniqueException("Ticket  "+ ticket.getId()+ " is already exists"+e.getMessage());
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
	       ticketRepo.deleteById(ticketId);
	    }
	    public void deleteAll() {
	        ticketRepo.deleteAll();
	     }
	    public void deleteAll(Ticket ticket) {
	        ticketRepo.delete(ticket);
	     }
	    //getter & setters
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
}
