package com.example.bankingsystem.web.controller;


import com.example.bankingsystem.web.form.ClientRegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/client/registration")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("registrationForm", new ClientRegistrationForm());
        modelAndView.setViewName("/client/registration");
        return modelAndView;
    }

    @GetMapping("/client/delete")
    public ModelAndView clientRemoval(ModelAndView modelAndView) {
        modelAndView.addObject("deleteForm", new ClientRegistrationForm());
        modelAndView.setViewName("/client/delete");
        return modelAndView;
    }

}
