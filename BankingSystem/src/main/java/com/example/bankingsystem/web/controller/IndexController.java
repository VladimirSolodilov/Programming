package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @GetMapping("/page/authorization")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("authorization", new Client());
        modelAndView.setViewName("/page/authorization");
        return modelAndView;
    }
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}
