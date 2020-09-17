package com.project.tb.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.tb.models.QRCode;
import com.project.tb.services.QRCodeServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {
@Autowired
QRCodeServices qrCodeServices;
@PreAuthorize("hasRole('USER')")
@GetMapping("/findByEmail/{email}")
public List<QRCode> findByEmail(@PathVariable String email) {
	return qrCodeServices.findByEmail(email);
}
}
