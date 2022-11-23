package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPageUnauthorized(Model model) {
        model.addAttribute("title", "Банковская система (неавторизованный пользователь)");
        return "/page/indexUnauthorized";
    }

    @GetMapping("/page/indexAuthorized")
    public String indexPageAuthorized(Model model) {
        model.addAttribute("title", "Банковская система (авторизованный пользователь)");
        return "/page/indexAuthorized";
    }


    @RequestMapping(value = "/page/authorization", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loginPage() {
        return new ModelAndView("/page/authorization");
    }

    /*@GetMapping("/page/authorization")
    public ModelAndView clientAuthorization(ModelAndView modelAndView) {
        modelAndView.addObject("authorization", new Client());
        modelAndView.setViewName("/page/authorization");
        return modelAndView;
    }*/

    /*@PostMapping("/page/authorization")
    public String clientAuthorizationPost() {
        return "redirect:/";
    }*/

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "/page/authorization";
    }
}
