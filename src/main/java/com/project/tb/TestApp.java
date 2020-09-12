package com.project.tb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
	}
}
