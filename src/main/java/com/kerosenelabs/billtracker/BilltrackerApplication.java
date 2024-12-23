package com.kerosenelabs.billtracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@OpenAPIDefinition(
		servers = {
				@Server(url = "https://billtracker.kerosenelabs.com/api/v1", description = "Production"),
				@Server(url = "https://dev.billtracker.kerosenelabs.com", description = "Development"),
				@Server(url = "http://localhost:8080", description = "Local")
		}
)
@CrossOrigin()
@SpringBootApplication
public class BilltrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilltrackerApplication.class, args);
	}

}
