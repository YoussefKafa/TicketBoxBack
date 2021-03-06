package com.project.tb.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.tb.models.QRCode;
import com.project.tb.payload.ConfirmRequest;
import com.project.tb.payload.ConfirmTicketResponse;
import com.project.tb.services.QRCodeServices;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {
@Autowired
QRCodeServices qrCodeServices;
@Secured({"ROLE_ADMIN","ROLE_USER","ROLE_DISTU"})
@GetMapping("/findByEmail/{email}")
public List<QRCode> findByEmail(@PathVariable String email) {
	return qrCodeServices.findByEmail(email);
}
@PreAuthorize("hasRole('ADMIN')")
@PostMapping("/conf")
public ConfirmTicketResponse confirm(@Valid @RequestBody ConfirmRequest confirmRequest) {
	return qrCodeServices.confirm(confirmRequest.getContent());
}
}
