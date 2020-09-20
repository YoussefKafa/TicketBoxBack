package com.project.tb.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.BookRequestRepo;
import com.project.tb.dao.QRCodeRepo;
import com.project.tb.models.QRCode;
import com.project.tb.payload.ConfirmTicketResponse;
import com.project.tb.security.AES;
import com.project.tb.security.SecurityConstants;
@Service
public class QRCodeServices {
	@Autowired
	QRCodeRepo qrCodeRepo;
	@Autowired
	BookRequestRepo bookRequestRepo;
public void confirm() {
	
}
public List<QRCode> findByEmail(String email){
	return qrCodeRepo.findByEmail(email);
}
public ConfirmTicketResponse confirm(String content) {
	ConfirmTicketResponse confirmTicketResponse=new ConfirmTicketResponse();
	String type="";
	boolean bookRequestExists=false;
	confirmTicketResponse.setType("false");
	boolean qrCodeExistsBoolean=false;
	Integer qrCodeExists=0;
	String bookId="";
	String decryptContent="";
	try {
		decryptContent=AES.decrypt(content, SecurityConstants.secretKey);
		if(decryptContent.contains("golden")) {
			confirmTicketResponse.setType("golden");
			return confirmTicketResponse;
		}
		 bookId=decryptContent.substring(decryptContent.indexOf("#")+1);
		bookRequestExists=bookRequestRepo.existsById(Long.parseLong(bookId));
	    qrCodeExists=qrCodeRepo.findByBookId(Long.parseLong(bookId));
	} catch (Exception e) {
		confirmTicketResponse.setType("invalid");
	return confirmTicketResponse;
	}
  if(qrCodeExists>0) qrCodeExistsBoolean=true;
	if(bookRequestExists && qrCodeExistsBoolean) {type="true";
	confirmTicketResponse.setType(type);
		qrCodeRepo.deleteByBookId(Long.parseLong(bookId));}
	return confirmTicketResponse;

}
}
