package com.example.colloquium.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.colloquium.domain.repository")
@EntityScan("com.example.colloquium.*")
@ComponentScan(basePackages = "com.example.colloquium.*")
public class ColloquiumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColloquiumApplication.class, args);
    }

}
