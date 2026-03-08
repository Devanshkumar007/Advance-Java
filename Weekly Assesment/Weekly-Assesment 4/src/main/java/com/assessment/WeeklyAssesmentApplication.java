package com.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeeklyAssesmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeeklyAssesmentApplication.class, args);
    }
}
