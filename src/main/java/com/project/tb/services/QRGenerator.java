package com.project.tb.services;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO; 
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.WriterException; 
import com.google.zxing.client.j2se.MatrixToImageWriter; 
import com.google.zxing.common.BitMatrix;
public class QRGenerator { 
    public static String createQR( 
        String qrCodeData) 
        throws WriterException, IOException 
    { 
        BitMatrix matrix 
            = new MultiFormatWriter().encode( 
                new String( 
                    qrCodeData.getBytes("UTF-8"), 
                    "UTF-8"), 
                BarcodeFormat.QR_CODE, 200, 200); 
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(MatrixToImageWriter 
                .toBufferedImage(matrix), "jpg", baos);
        byte[] bytes = baos.toByteArray();
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }
} 