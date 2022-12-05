package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @GetMapping("/authorized/person/account")
    public String personAccount(Model model, Authentication authentication) {
        model.addAttribute("person", juridicalPersonService.getPersonList(authentication.getName()));
        model.addAttribute("title", "Личный кабинет юридического лица");
        return "/person/account";
    }

    @GetMapping("/person/signUp")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("personSignUp", new JuridicalPerson());
        modelAndView.setViewName("/person/personSignUp");
        return modelAndView;
    }

    @PostMapping("/person/signUp")
    public String clientRegistrationPost(Model model, JuridicalPerson juridicalPerson) {
        /*if (bindingResult.hasErrors()) {
            return "/person/registration";
        }
        if (!juridicalPerson.getPassword().equals(juridicalPerson.getPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/client/registration";
        }
        if (!juridicalPersonService.saveClient(juridicalPerson)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/client/registration";
        }*/
        model.addAttribute(juridicalPersonService.setPersonList(1, 3,
                juridicalPerson.getSurname(), juridicalPerson.getName(), juridicalPerson.getPatronymic(),
                juridicalPerson.getJuridicalPersonName(), juridicalPerson.getPassword(), juridicalPerson.getSum()));

        return "redirect:/";
    }
    @GetMapping("/authorized/person/addSum")
    public String addSum(Model model, Authentication authentication) {
        model.addAttribute("person", juridicalPersonService.getPersonList(authentication.getName()));
        model.addAttribute("personAddSum", new JuridicalPerson());
        return "/person/addSum";
    }
    @PostMapping("/authorized/person/addSum")
    public String addSumPost(Model model, JuridicalPerson juridicalPerson, Authentication authentication) {
        model.addAttribute(juridicalPersonService.addSum(authentication.getName(), juridicalPerson.getSum()));
        return "redirect:/authorized/person/account";
    }
    @GetMapping("/authorized/person/transfer")
    public String transfer(Model model, Authentication authentication) {
        model.addAttribute("personLeft", juridicalPersonService.getPersonList(authentication.getName()));
        model.addAttribute("personRight", juridicalPersonService.getPersonList("admin"));
        return "/person/transfer";
    }

    @PostMapping("/authorized/person/transfer")
    public String transferPost(Model model, Authentication authentication, JuridicalPerson juridicalPerson) {
        model.addAttribute(juridicalPersonService.transfer(authentication.getName(), juridicalPerson.getName(), juridicalPerson.getSum()));
        return "redirect:/authorized/person";
    }

    @GetMapping("/authorized/person/transferInfo")
    public String transferInfo(Model model, Authentication authentication) {
        model.addAttribute("personTransferInfo", juridicalPersonService.transferInfo(authentication.getName()));
        return "/person/transferInfo";
    }

    /*@GetMapping("/person/removal")
    public ModelAndView clientRemoval(ModelAndView modelAndView) {
        modelAndView.addObject("removal", new Client());
        modelAndView.setViewName("/person/removal");
        return modelAndView;
    }

    @PostMapping("/person/removal")
    public String deleteMailPost(Model model) {
        model.addAttribute(juridicalPersonService.deletePersonList(juridicalPerson.getSurname()));
        return informationPerson(model);
    }*/


}
