package com.project.tb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tb.dao.BookRequestRepo;
import com.project.tb.dao.QRCodeRepo;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.QRCode;
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
public boolean confirm(String content) {
	boolean bookRequestExists=false;
	boolean confirmed=false;
	boolean qrCodeExistsBoolean=false;
	Integer qrCodeExists=0;
	String bookId="";
	String decryptContent="";
	try {
		decryptContent=AES.decrypt(content, SecurityConstants.secretKey);
		 bookId=decryptContent.substring(decryptContent.indexOf("#")+1);
		bookRequestExists=bookRequestRepo.existsById(Long.parseLong(bookId));
	    qrCodeExists=qrCodeRepo.findByBookId(Long.parseLong(bookId));
	} catch (Exception e) {
	throw new ModelException("Invalid Ticket");
	}
  if(qrCodeExists>0) qrCodeExistsBoolean=true;
	if(bookRequestExists && qrCodeExistsBoolean) {confirmed =true;
		qrCodeRepo.deleteByBookId(Long.parseLong(bookId));}
	return confirmed;

}
}
