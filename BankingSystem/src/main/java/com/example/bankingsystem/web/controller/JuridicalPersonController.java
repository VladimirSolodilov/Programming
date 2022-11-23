package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class JuridicalPersonController {

    @Autowired
    private JuridicalPersonService juridicalPersonService;

    private JuridicalPerson juridicalPerson = new JuridicalPerson();

    /*@GetMapping("/main/person/information")
    public String informationClient(Model model) {
        model.addAttribute("person", juridicalPerson.getClientList());
        model.addAttribute("title", "Личный кабинет клиента");
        return "/person/information";
    }*/

    @GetMapping("/person/registration")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("registration", new JuridicalPerson());
        modelAndView.setViewName("/person/registration");
        return modelAndView;
    }

    /*@PostMapping("/person/registration")
    public String clientRegistrationPost(Model model) {
        model.addAttribute(juridicalPersonService.set(client.getClientId(), client.getBranchId(), client.getRoleId(), client.getSurname(),
                client.getName(), client.getPatronymic(), client.getClientName(), client.getPassword(), client.getSum()));

        return informationClient(model);
    }*/

    @GetMapping("/person/removal")
    public ModelAndView clientRemoval(ModelAndView modelAndView) {
        modelAndView.addObject("removal", new Client());
        modelAndView.setViewName("/person/removal");
        return modelAndView;
    }

    /*@PostMapping("/person/removal")
    public String deleteMailPost(Model model) {
        model.addAttribute(clientService.deleteClientList(client.getSurname()));
        return informationClient(model);
    }*/


}
