package com.example.albartros;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "My API",
                version = "1.0",
                description = "Bu API orqali mahsulotlarni boshqarish mumkin"
        )
)
@SpringBootApplication
public class AlbatrosTourOperatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlbatrosTourOperatorApplication.class, args);
    }

}
