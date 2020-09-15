package com.project.tb.services;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.TicketRepo;
import com.project.tb.dao.UserRepo;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.Ticket;
import com.project.tb.payload.TicketScanResult;
@Service
public class TicketServices {
	 @Autowired
	    private TicketRepo ticketRepo;
	 @Autowired
	    private UserRepo userRepo;
		public Ticket saveOrUpdate(final Ticket ticket) {
	        try {
	        if(ticket.getId()==null) {
	            return ticketRepo.save(ticket);}
	        else {
	        
	        	Optional<Ticket> ticket2=ticketRepo.findById(ticket.getId());
	        	Ticket ticket22=ticket2.get();
	        	System.out.println(ticket22.getCreatedAt());
	        	Date updateDate=new Date();
	        	ticket22.setCounter(ticket.getCounter());
	        	ticket22.setEndDate(ticket.getEndDate());
	        	ticket22.setGates(ticket.getGates());
	        	ticket22.setPrice(ticket.getPrice());
	        	ticket22.setReleaseDate(ticket.getReleaseDate());
	        	ticket22.setReturnDate(ticket.getReturnDate());
	        	ticket22.setReturnable(ticket.isReturnable());
	        	ticket22.setTicketSequence(ticket.getTicketSequence());
	        	ticket22.setQrCode(ticket.getQrCode());
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
		public void decreaseCounter(Long ticket_id) {
			ticketRepo.decreaseCounter(ticket_id);
		}
	public boolean existsByTicketSequence(String ticketSequence) {
		return ticketRepo.existsByTicketSequence(ticketSequence);
	}
	public TicketScanResult scan(String content) {
		
		try {
			String preFilePath="D:\\TicketBoxBack\\rsrc\\qrCodes\\";
			String fileName=content.substring(content.indexOf("&")+1, content.length());
			String fullPath=preFilePath+=fileName;
			String name=content.substring(0, content.indexOf("$"));
			String ticketSequence=content.substring(content.indexOf("$")+1, content.indexOf("&"));
			String email=content.substring(content.indexOf("&")+1,content.indexOf("#"));
			boolean isTicketSequence=false,isEmail=false,isFile=false;
			isTicketSequence=ticketRepo.existsByTicketSequence(ticketSequence);
		File tryFile=new File(fullPath);
		if(tryFile.exists()) isFile=true;
		isEmail=userRepo.existsByEmail(email);
			boolean confirmed=isTicketSequence&&isEmail&&isFile;
			return new TicketScanResult(name,ticketSequence,fullPath,email,confirmed);
		} catch (Exception e) {
			throw new ModelException("Enter a valid Ticket QR");
		}
	}
		
	}
	
