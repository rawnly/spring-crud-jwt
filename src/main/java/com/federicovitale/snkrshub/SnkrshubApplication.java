package com.federicovitale.snkrshub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableWebSecurity
public class SnkrshubApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnkrshubApplication.class, args);
    }
}
