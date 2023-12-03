package com.ldb.skillsdemo.bkend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BkendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkendApplication.class, args);
    }


    @Bean
    public OpenAPI apiDoc() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo API")
                        .description("Refreshin Skills"));
    }
}
