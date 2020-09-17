package com.project.tb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tb.dao.QRCodeRepo;
import com.project.tb.models.QRCode;
@Service
public class QRCodeServices {
	@Autowired
	QRCodeRepo qrCodeRepo;
public void confirm() {
	
}
public List<QRCode> findByEmail(String email){
	return qrCodeRepo.findByEmail(email);
}
}
