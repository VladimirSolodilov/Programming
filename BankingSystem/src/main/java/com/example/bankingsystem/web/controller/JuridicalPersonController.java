package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JuridicalPersonController {

    @Autowired
    private JuridicalPersonService juridicalPersonService;

    private final JuridicalPerson juridicalPerson = new JuridicalPerson();

    @GetMapping("/main/person/information")
    public String informationPerson(Model model) {
        model.addAttribute("person", juridicalPersonService.getPersonList());
        model.addAttribute("title", "Личный кабинет клиента");
        return "/person/information";
    }

    @GetMapping("/person/registration")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("personRegistration", new JuridicalPerson());
        modelAndView.setViewName("/person/registration");
        return modelAndView;
    }

    @PostMapping("/person/registration")
    public String clientRegistrationPost(Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/person/registration";
        }
        if (!juridicalPerson.getPassword().equals(juridicalPerson.getPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/client/registration";
        }
        if (!juridicalPersonService.saveClient(juridicalPerson)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/client/registration";
        }
        model.addAttribute(juridicalPersonService.setPersonList(juridicalPerson.getJuridicalPersonId(), juridicalPerson.getBranchId(), juridicalPerson.getRoleId(),
                juridicalPerson.getSurname(), juridicalPerson.getName(), juridicalPerson.getPatronymic(),
                juridicalPerson.getJuridicalPersonName(), juridicalPerson.getPassword(), juridicalPerson.getSum()));

        return informationPerson(model);
    }

    @GetMapping("/person/removal")
    public ModelAndView clientRemoval(ModelAndView modelAndView) {
        modelAndView.addObject("removal", new Client());
        modelAndView.setViewName("/person/removal");
        return modelAndView;
    }

    @PostMapping("/person/removal")
    public String deleteMailPost(Model model) {
        model.addAttribute(juridicalPersonService.deletePersonList(juridicalPerson.getSurname()));
        return informationPerson(model);
    }


}
