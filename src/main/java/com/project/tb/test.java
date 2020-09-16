package com.project.tb;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.hibernate.boot.model.source.internal.hbm.AbstractEntitySourceImpl;

import com.google.zxing.NotFoundException;
import com.project.tb.security.AES;
import com.project.tb.security.SecurityConstants;
import com.project.tb.services.QRCodeServices;

public class test {
	public static void main(String[] args) throws FileNotFoundException, NotFoundException, IOException {
	String reading=QRCodeServices.readQRCode("youssefkafa@gmail.com#32423523523$0$");
	
	}
}
