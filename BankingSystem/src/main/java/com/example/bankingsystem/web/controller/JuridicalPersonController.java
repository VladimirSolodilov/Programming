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
import java.util.Objects;

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
    public ModelAndView transfer(ModelAndView modelAndView, Authentication authentication) {
        modelAndView.addObject("personLeft", juridicalPersonService.getPersonList(authentication.getName()));
        modelAndView.addObject("personRight", juridicalPersonService.getPersonList("admin"));
        modelAndView.addObject("personSumTransfer", new JuridicalPerson());
        modelAndView.setViewName("/person/transfer");
        return modelAndView;
    }

    @PostMapping("/authorized/person/transfer")
    public ModelAndView transferPost(ModelAndView modelAndView, Authentication authentication, JuridicalPerson juridicalPerson) {
        if (juridicalPerson.getAccount().getSum() > juridicalPersonService.getPersonList(authentication.getName()).get(0).getAccount().getSum()) {
            return transfer(modelAndView.addObject("transferError", "Error Message"), authentication);
        } else {
            modelAndView.addObject(juridicalPersonService.transfer(authentication.getName(), juridicalPerson.getJuridicalPersonName().substring(juridicalPerson.getJuridicalPersonName().lastIndexOf(':') + 2), juridicalPerson.getAccount().getSum()));
            modelAndView.setViewName("redirect:/authorized");
            return modelAndView;
        }
    }

    @GetMapping("/authorized/person/transferInfo")
    public String transferInfo(Model model, Authentication authentication) {
        model.addAttribute("personTransferInfo", juridicalPersonService.transferInfo(authentication.getName()));
        return "/person/transferInfo";
    }

    @GetMapping("/authorized/person/createPayment")
    public ModelAndView createTransfer(ModelAndView modelAndView, Authentication authentication) {
        modelAndView.addObject("person", juridicalPersonService.getPersonList(authentication.getName()));
        modelAndView.addObject("client", clientService.getClientList("admin"));
        modelAndView.addObject("personCreatePayment", new Payment());
        modelAndView.setViewName("/person/createPayment");
        return modelAndView;
    }

    @PostMapping("/authorized/person/createPayment")
    public ModelAndView createTransferPost(ModelAndView modelAndView, Authentication authentication, Payment payment, Client client, JuridicalPerson juridicalPerson) {
            List<JuridicalPerson> juridicalPersonList = juridicalPersonService.getPersonList(authentication.getName());
            JuridicalPerson juridicalPerson1 = juridicalPersonList.get(0);
            modelAndView.addObject(paymentService.createPayment(juridicalPerson1.getJuridicalPersonId(), client.getClientName(),payment.getName(), payment.getSum(), payment.getPurpose().getPurposeName()));
            modelAndView.setViewName("redirect:/authorized");
            return modelAndView;
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
