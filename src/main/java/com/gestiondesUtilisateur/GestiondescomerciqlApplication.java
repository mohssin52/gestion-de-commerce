package com.gestiondesUtilisateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestiondescomerciqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondescomerciqlApplication.class, args);
	}
	@Bean
public BCryptPasswordEncoder bCreaptPasswordEncoder() {
	return new BCryptPasswordEncoder();
	 
}@Bean
	public SpringApplicationContext springApplicationContext() {
		return new  SpringApplicationContext();
	}
}
