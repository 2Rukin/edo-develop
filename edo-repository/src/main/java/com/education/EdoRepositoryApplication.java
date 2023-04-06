package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class EdoRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdoRepositoryApplication.class, args);
    }
}
