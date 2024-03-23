package com.nk.security;

import com.nk.security.auth.AuthenticationService;
import com.nk.security.auth.RegisterRequest;
import com.nk.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import static com.nk.security.user.Role.ADMIN;
import static com.nk.security.user.Role.MANAGER;

@Configuration
@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService authenticationService
	){
		return args -> {
			RegisterRequest admin = RegisterRequest.builder()
					.firstName("Admin")
					.lastName("Admin")
					.password("admin")
					.role(ADMIN)
					.email("admin@mail.com")
					.build();

			System.out.println("Admin token: " + authenticationService.register(admin).getToken());

			RegisterRequest manager = RegisterRequest.builder()
					.firstName("Manager")
					.lastName("Manager")
					.password("manager")
					.role(MANAGER)
					.email("manager@mail.com")
					.build();

			System.out.println("Manager token: " + authenticationService.register(manager).getToken());

		};
	}
}
