package com.project.tb.services;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeServices {
	static String charset = "UTF-8";
	static String filePath = "D:\\TicketBoxBack\\rsrc\\qrCodes\\";
	public static void createQRCode(String qrCodeData, String email)
			throws WriterException, IOException {
		email+=".png";
		filePath+=email;
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, 200, 200, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readQRCode(String email)
			throws FileNotFoundException, IOException, NotFoundException {
		email+=".png";
		filePath+=email;
		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
}
