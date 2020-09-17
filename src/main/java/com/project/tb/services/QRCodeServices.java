package com.project.tb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tb.dao.BookRequestRepo;
import com.project.tb.dao.QRCodeRepo;
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
	boolean bookRequestExists;
	Integer qrCodeExists;
	boolean confirmed=false;
	String decryptContent=AES.decrypt(content, SecurityConstants.secretKey);
	String bookId=decryptContent.substring(decryptContent.indexOf("#")+1);
	bookRequestExists=bookRequestRepo.existsById(Long.parseLong(bookId));
    qrCodeExists=qrCodeRepo.findByBookId(Long.parseLong(bookId));
  boolean qrCodeExistsBoolean=false;
  if(qrCodeExists>0) qrCodeExistsBoolean=true;
	if(bookRequestExists && qrCodeExistsBoolean) {confirmed =true;
		qrCodeRepo.deleteByBookId(Long.parseLong(bookId));}
	return confirmed;

}
}
