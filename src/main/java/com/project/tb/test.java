package com.project.tb;

import java.io.IOException;

import com.google.zxing.WriterException;
import com.project.tb.security.AES;
import com.project.tb.security.SecurityConstants;
import com.project.tb.services.QRGenerator;

public class test {

	public static void main(String[] args) throws WriterException, IOException {
	System.out.println(AES.encrypt("golden DrBaseemBarhom", SecurityConstants.secretKey));
	System.out.println(QRGenerator.createQR(AES.encrypt("golden DrBaseemBarhom", SecurityConstants.secretKey)));
	}

}
