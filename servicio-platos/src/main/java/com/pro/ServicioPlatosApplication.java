package com.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicioPlatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioPlatosApplication.class, args);
	}

}
