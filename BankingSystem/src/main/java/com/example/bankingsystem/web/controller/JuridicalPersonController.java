package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.branch.BranchService;
import com.example.bankingsystem.domain.client.ClientService;
import com.example.bankingsystem.domain.model.Branch;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Payment;
import com.example.bankingsystem.domain.payment.PaymentService;
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

import java.util.List;

@Controller
public class JuridicalPersonController {
    @Autowired
    private JuridicalPersonService juridicalPersonService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/authorized/person/account")
    public String personAccount(Model model, Authentication authentication) {
        model.addAttribute("person", juridicalPersonService.getPersonList(authentication.getName()));
        model.addAttribute("title", "Личный кабинет юридического лица");
        return "/person/account";
    }

    @GetMapping("/person/signUp")
    public ModelAndView personRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("personSignUp", new JuridicalPerson());
        modelAndView.addObject("branchList", branchService.getBranchList());
        modelAndView.setViewName("/person/personSignUp");
        return modelAndView;
    }

    @PostMapping("/person/signUp")
    public ModelAndView personRegistrationPost(ModelAndView modelAndView, JuridicalPerson juridicalPerson, Branch branch) {
        if (juridicalPersonService.getPersonList(juridicalPerson.getJuridicalPersonName()).size() != 0) {
            return personRegistration(modelAndView.addObject("registrationError", "Error message"));
        } else {
            List<Branch> branchList = branchService.getBranchIdByName(branch.getBranchName());
            modelAndView.addObject(juridicalPersonService.setPersonList(branchList.get(0).getBranchId(), 3,
                    juridicalPerson.getSurname(), juridicalPerson.getName(), juridicalPerson.getPatronymic(), juridicalPerson.getOrganizationName(),
                    juridicalPerson.getJuridicalPersonName(), juridicalPerson.getPassword(), 0));
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
    }
    @GetMapping("/authorized/person/addSum")
    public String addSum(Model model, Authentication authentication) {
        model.addAttribute("person", juridicalPersonService.getPersonList(authentication.getName()));
        model.addAttribute("personAddSum", new JuridicalPerson());
        return "/person/addSum";
    }
    @PostMapping("/authorized/person/addSum")
    public String addSumPost(Model model, JuridicalPerson juridicalPerson, Authentication authentication) {
        model.addAttribute(juridicalPersonService.addSum(authentication.getName(), juridicalPerson.getAccount().getSum()));
        return "redirect:/authorized/person/account";
    }
    @GetMapping("/authorized/person/transfer")
    public String transfer(Model model, Authentication authentication) {
        model.addAttribute("personLeft", juridicalPersonService.getPersonList(authentication.getName()));
        model.addAttribute("personRight", juridicalPersonService.getPersonList("admin"));
        model.addAttribute("personSumTransfer", new JuridicalPerson());
        return "/person/transfer";
    }

    @PostMapping("/authorized/person/transfer")
    public String transferPost(Model model, Authentication authentication, JuridicalPerson juridicalPerson) {
        model.addAttribute(juridicalPersonService.transfer(authentication.getName(), juridicalPerson.getJuridicalPersonName().substring(juridicalPerson.getJuridicalPersonName().lastIndexOf(':') + 2), juridicalPerson.getAccount().getSum()));
        return "redirect:/authorized";
    }

    @GetMapping("/authorized/person/transferInfo")
    public String transferInfo(Model model, Authentication authentication) {
        model.addAttribute("personTransferInfo", juridicalPersonService.transferInfo(authentication.getName()));
        return "/person/transferInfo";
    }

    @GetMapping("/authorized/person/createPayment")
    public String transferCreate(Model model, Authentication authentication) {
        model.addAttribute("person", juridicalPersonService.getPersonList(authentication.getName()));
        model.addAttribute("client", clientService.getClientList("admin"));
        model.addAttribute("personCreatePayment", new Payment());
        return "/person/createPayment";
    }

    @PostMapping("/authorized/person/createPayment")
    public String transferCreatePost(Model model, Authentication authentication, Payment payment, Client client) {
        List<JuridicalPerson> juridicalPersonList = juridicalPersonService.getPersonList(authentication.getName());
        JuridicalPerson juridicalPerson = juridicalPersonList.get(0);

        model.addAttribute(paymentService.createPayment(juridicalPerson.getJuridicalPersonId(), client.getClientName(),payment.getName(), payment.getSum(), payment.getPurpose().getPurposeName()));
        return "redirect:/authorized";
    }

    @GetMapping("/authorized/person/viewPayment")
    public String viewPayment(Model model, Authentication authentication) {
        model.addAttribute("person", juridicalPersonService.getPersonList(null));
        model.addAttribute("viewPayment", paymentService.getPaymentList(authentication.getName(), null));
        return "/person/viewPayment";
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
