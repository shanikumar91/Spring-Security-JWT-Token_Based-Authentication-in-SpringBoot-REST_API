package com.springbootproject.productslist;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info=@Info(
                title = "Product Service REST API Documentation",
                description = "Product Service REST API",
                version = "v1",
                contact = @Contact(
                        name = "Shani kumar",
                        email = "shanikumar87915688@gmail.com"
                )

        ),
        externalDocs = @ExternalDocumentation(
                description = "Sharepoint URL Product Service API",
                url = "example.com"
        )
)

@SpringBootApplication
public class ProductsListApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProductsListApplication.class, args);
        System.out.println("Hare krishna hare  Krishna");
	}

}
