package com.project.tb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.tb.services.CreditRequestServices;
@SpringBootApplication
@EnableJpaAuditing
public class OnlyatestApplication {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		// we've created this bean here so we can autowired it with user services
		return new BCryptPasswordEncoder();
	}
	@Bean
	CreditRequestServices creditRequestServices() {
		return new CreditRequestServices();
	}
	public static void main(String[] args) {
		SpringApplication.run(OnlyatestApplication.class, args);
	}
}
