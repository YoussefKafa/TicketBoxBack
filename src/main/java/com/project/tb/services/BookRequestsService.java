package com.project.tb.services;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.zxing.WriterException;
import com.project.tb.dao.BookRequestRepo;
import com.project.tb.dao.GameRepo;
import com.project.tb.dao.QRCodeRepo;
import com.project.tb.dao.TicketRepo;
import com.project.tb.dao.UserRepo;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.QRCode;
import com.project.tb.models.Ticket;
import com.project.tb.models.User;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.project.tb.payload.BookRequests;
import com.project.tb.payload.FindTicketsByEmailResponse;
import com.project.tb.security.AES;
import com.project.tb.security.SecurityConstants;
public class BookRequestsService {

	@Autowired
	BookRequestRepo bookRequestRepo;
	@Autowired
	TicketRepo ticketRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	GameRepo gameRepo;
	@Autowired
	QRCodeRepo qrCodeRepo;
	public List<BookRequests> findByEmail(String email)
	{
		return bookRequestRepo.findByEmail(email);
	}
	public void save(BookRequests bookRequest) throws WriterException, IOException {
		Optional<Ticket> tikTicket=ticketRepo.findById(bookRequest.getTicketId());
		int price=tikTicket.get().getPrice();
		Optional<User> user=userRepo.findById(userRepo.getIdFromEmail(bookRequest.getEmail()));
		int credit=user.get().getCredit();
		if(credit>=price) {
		userRepo.decreaseCredit(price, userRepo.findById(userRepo.getIdFromEmail(bookRequest.getEmail())).get().getId());}
		else {
			throw new ModelException("Dear User: You don't have enough credit!");
		}
		gameRepo.increaseSaleCounter(tikTicket.get().getGame().getId());
		ticketRepo.decreaseCounter(bookRequest.getTicketId());
		bookRequestRepo.save(bookRequest);
		String qrCodeData=bookRequest.getEmail()+"$"+tikTicket.get().getTicketSequence()+"#"+bookRequest.getId();
		String encodedQrCodeData=AES.encrypt(qrCodeData, SecurityConstants.secretKey);
		QRCode qrCode =new QRCode(bookRequest.getEmail(), tikTicket.get().getTicketSequence(), QRGenerator.createQR(encodedQrCodeData), bookRequest);
		qrCodeRepo.save(qrCode);
	}
	public void delete(Long id) {
		bookRequestRepo.deleteById(id);
	}

}
