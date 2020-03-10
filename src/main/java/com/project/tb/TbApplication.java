package com.project.tb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@SpringBootApplication
public class TbApplication {
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		//we've created this bean here so we can autowired it with user services
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(TbApplication.class, args);
	}
}
