package com.orthoproducts.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		System.out.println("This is the API Gateway");

		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
