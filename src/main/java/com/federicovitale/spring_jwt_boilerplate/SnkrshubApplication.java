package com.federicovitale.spring_jwt_boilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SnkrshubApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnkrshubApplication.class, args);
    }
}
