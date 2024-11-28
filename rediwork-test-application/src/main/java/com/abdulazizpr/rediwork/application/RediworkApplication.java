package com.abdulazizpr.rediwork.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackages = {"com.abdulazizpr.rediwork.repository"})
@SpringBootApplication(scanBasePackages = "com.abdulazizpr.rediwork")
public class RediworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(RediworkApplication.class, args);
    }
}
