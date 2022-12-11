package com.example.bankingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BankingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
    }
}
