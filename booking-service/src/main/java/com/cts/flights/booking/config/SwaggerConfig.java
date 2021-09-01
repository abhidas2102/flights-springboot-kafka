package com.cts.flights.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public Docket updateSwaggerInfo(){
        return new Docket(DocumentationType.SWAGGER_2)

        .select()
        .apis(RequestHandlerSelectors.basePackage("com.cts.flights.booking"))
        .build()
        .apiInfo(apiInfo())
        ;
    }
    

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
        .title("Free Flights Booking Service")
        .description("Free Flights Booking Service")
        .version("1.0")
        .contact(new Contact("Abhishek", "www.cognizant.com", "cognizant@cognizant.com"))
        .license("CTS")
        .build();
    }

}