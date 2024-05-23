package com.jhonny.coffee.webflux.postgresql;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Coffee Webflux Application" , version = "1.0" , description = "API de documentation v1.0"))
@SpringBootApplication
public class CoffeeWebfluxPostgresqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeWebfluxPostgresqlApplication.class, args);
	}

}
