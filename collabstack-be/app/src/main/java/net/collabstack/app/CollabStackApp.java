package com.collabstack.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.collabstack")
public class CollabStackApp {

    public static void main(final String[] args) {
        SpringApplication.run(CollabStackApp.class, args);
    }

}
