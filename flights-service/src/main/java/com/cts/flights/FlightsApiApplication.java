package com.cts.flights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableDiscoveryClient
public class FlightsApiApplication {

	public static void main(String[] args) { 
		SpringApplication.run(FlightsApiApplication.class, args);
	}

}
