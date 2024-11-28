package com.abdulazizpr.rediwork.migration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories("com.abdulazizpr.rediwork.repository")
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class DataMigration {
    public static void main(String[] args) {
        SpringApplication.run(DataMigration.class, args);
    }
}