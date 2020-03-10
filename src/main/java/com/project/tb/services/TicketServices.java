package com.project.tb.services;
import java.util.ArrayList;
import java.util.List;
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
	            throw new TicketUniqueException("Ticket  "+ ticket.getId()+ " is already exists");
	        }
	        
	    }
	    public List<Ticket> findAll() {
	        var it = ticketRepo.findAll();
	        var tickets = new ArrayList<Ticket>();
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
}
