package com.kaweel.rookieworkshopspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RookieWorkshopSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RookieWorkshopSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		System.out.println("Hello Rookie Spring Boot!!");
        System.exit(0);
    }
}
