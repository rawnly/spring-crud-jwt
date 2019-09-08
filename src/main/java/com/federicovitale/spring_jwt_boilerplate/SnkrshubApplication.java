package com.federicovitale.spring_jwt_boilerplate;

import com.federicovitale.spring_jwt_boilerplate.utils.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.sql.Date;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SnkrshubApplication {
    public static void main(String[] args) {
        SpringApplication.run(SnkrshubApplication.class, args);
    }
}
