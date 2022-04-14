package com.example.myprojectsite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping
    public String defaultPage(){
        return "/signin";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup.html";
    }

    @GetMapping("/signin")
    public String signinPage(){
        return "signin.html";
    }

    @GetMapping("/profile")
    public String profilePage(){
        return "profile";
    }

}
