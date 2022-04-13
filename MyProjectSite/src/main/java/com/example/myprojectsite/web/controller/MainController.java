package com.example.myprojectsite.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    @GetMapping
    public String defaultPage(){
        return "/identification/signin.html";
    }

    @GetMapping("/identification/signup")
    public String signupPage(){
        return "signup.html";
    }

    @GetMapping("/identification/signin")
    public String signinPage(){
        return "signin.html";
    }

    @GetMapping("/profile")
    public String profilePage(){
        return "profile";
    }

}
