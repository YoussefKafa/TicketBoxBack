package com.project.tb;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.zxing.NotFoundException;
import com.project.tb.services.QRCodeServices;

public class te {

	public static void main(String[] args) throws FileNotFoundException, NotFoundException, IOException {
		System.out.println(	QRCodeServices.readQRCode("youssefkafa@gmail.com$32423523523$0$")
);
	}

}
