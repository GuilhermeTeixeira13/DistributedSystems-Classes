package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class QuickStartApplication {
    public static void main(String[] args) {
      SpringApplication.run(QuickStartApplication.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "firstName", defaultValue = "John") String firstName,
    @RequestParam(value = "lastName", defaultValue = "Doe") String lastName) {
    return String.format("Hello %s %s!", firstName, lastName);
    }
}
