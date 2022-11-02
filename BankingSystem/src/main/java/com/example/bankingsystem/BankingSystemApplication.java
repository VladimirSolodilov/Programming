package com.example.bankingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class BankingSystemApplication {

    @GetMapping("/")
    public String HelloWorld() {
        return "Hello, world!";
    }

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
    }

}
