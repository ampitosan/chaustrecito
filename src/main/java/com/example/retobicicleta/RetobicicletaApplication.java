package com.example.retobicicleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages={"com.example.retobicicleta.Model" })
//@EnableJpaRepositories("Repository")

public class RetobicicletaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetobicicletaApplication.class, args);
    }

}
