package com.funnygorilla.tinybankaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages = {"com.funnygorilla.tinybankaccount"})
@OpenAPIDefinition(info = @Info(
		title = "Account Management Domain Service", 
		description = "An API showcase demonstrating how to automatically generate an openapi spec and client based on this.",
		contact = @Contact(
			    name = "Platform Architecture Team",
			    email = "platform@ing.com.au"),
		version = "0.0.1"		
	))
public class TinybankaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinybankaccountApplication.class, args);
	}

}
