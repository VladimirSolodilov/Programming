package com.example.bankingsystem.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {

    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("title", "Банковская система");
        return "/index";
    }

}
