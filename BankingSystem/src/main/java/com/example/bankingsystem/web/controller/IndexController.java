package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPageUnauthorized(Model model) {
        model.addAttribute("title", "Банковская система (неавторизованный пользователь)");
        return "/page/index";
    }

    @GetMapping("/authorized")
    public String indexPageAuthorized(Model model) {
        model.addAttribute("title", "Банковская система (авторизованный пользователь)");
        return "/page/index";
    }

    /*@GetMapping("/signIn/error")
    public String indexErrorPage(Model model) {
        model.addAttribute("title", "Ошибка");
        return "/page/error";
    }*/

    @GetMapping("/signIn")
    public ModelAndView clientSignUp(ModelAndView modelAndView) {
        modelAndView.addObject("signIn");
        modelAndView.setViewName("/page/signIn");
        return modelAndView;
    }

    @GetMapping("/person/signIn")
    public ModelAndView personSignUp(ModelAndView modelAndView) {
        modelAndView.addObject("personSignIn", new JuridicalPerson());
        modelAndView.setViewName("/page/signIn");
        return modelAndView;
    }

}
