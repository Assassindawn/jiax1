package com.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JiaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiaxApplication.class, args);
    }

}
