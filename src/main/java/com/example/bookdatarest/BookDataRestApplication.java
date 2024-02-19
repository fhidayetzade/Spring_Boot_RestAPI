package com.example.bookdatarest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition (
		info = @Info(
				title = "Book Service API",
				description = "Book Crud services",
				version = "v2"
		)
)
public class BookDataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookDataRestApplication.class, args);
	}

}
