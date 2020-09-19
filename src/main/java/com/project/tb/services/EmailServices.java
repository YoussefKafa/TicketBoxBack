package com.project.tb.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.tb.models.User;
@Service
public class EmailServices {
	@Autowired
    private JavaMailSender emailSender;
	public void sendMessage(User user) {
		String text="Hi "+ user.getName() + "\n" + 
				"Welcome to TicketBox. Your new account comes with access to TicketBox products, apps, and services.\r\n";
		String email=user.getEmail();
		   SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("TicketBoxSy@gmail.com");
	        message.setTo(email); 
	        message.setSubject("TicketBox Community"); 
	        message.setText(text);
	        emailSender.send(message);
	}
}
