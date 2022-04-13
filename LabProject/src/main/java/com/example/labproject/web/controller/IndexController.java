package com.example.labproject.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("title", "Лабораторная работа №3");
        return "index.html";
    }

}
