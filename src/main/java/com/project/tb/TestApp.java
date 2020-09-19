package com.project.tb;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.tb.services.BookRequestsService;
import com.project.tb.services.CreditRequestServices;
@SpringBootApplication
@EnableJpaAuditing
public class TestApp {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	@Bean
	CreditRequestServices creditRequestServices() {
		return new CreditRequestServices();
	}
	@Bean
	BookRequestsService bookRequestsService() {
		return new BookRequestsService();
	}
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("ticketboxsy@gmail.com");
	    mailSender.setPassword("#20TicketBox20#");
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.starttls.enable", "true");
	    return mailSender;
	}
	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
	}
}
