package com.example.bankingsystem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPageUnauthorized(Model model) {
        model.addAttribute("title", "Банковская система (неавторизованный пользователь)");
        return "/page/indexUnauthorized";
    }

    @GetMapping("/main")
    public String indexPageAuthorized(Model model) {
        model.addAttribute("title", "Банковская система (авторизованный пользователь)");
        return "/page/indexAuthorized";
    }





}
