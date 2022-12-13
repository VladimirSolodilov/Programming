package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.branch.BranchService;
import com.example.bankingsystem.domain.client.ClientService;
import com.example.bankingsystem.domain.model.*;
import com.example.bankingsystem.domain.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/authorized/client/account")
    public String informationClient(Model model, Authentication authentication) {
        model.addAttribute("client", clientService.getClientList(authentication.getName()));
        model.addAttribute("title", "Личный кабинет клиента");
        return "/client/account";
    }

    @GetMapping("/client/signUp")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("clientSignUp", new Client());
        modelAndView.addObject("branchList", branchService.getBranchList());
        modelAndView.setViewName("/client/clientSignUp");
        return modelAndView;
    }

    @PostMapping("/client/signUp")
    public ModelAndView clientRegistrationPost(ModelAndView modelAndView, Client client, Branch branch) {
        if (clientService.getClientList(client.getClientName()).size() != 0) {
            return clientRegistration(modelAndView.addObject("registrationError", "Error message"));
        } else {
            List<Branch> branchList = branchService.getBranchIdByName(branch.getBranchName());
            modelAndView.addObject(clientService.createClient(branchList.get(0).getBranchId(), 2, client.getSurname(),
                    client.getName(), client.getPatronymic(), client.getClientName(), client.getPassword(), 0));
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
    }

    @GetMapping("/authorized/client/removal")
    public ModelAndView clientRemoval(ModelAndView modelAndView) {
        modelAndView.addObject("removal", new Client());
        modelAndView.setViewName("/client/removal");
        return modelAndView;
    }

    @PostMapping("/authorized/client/removal")
    public String deleteMailPost(Model model, Client client) {
        model.addAttribute(clientService.deleteClient(client.getClientName()));
        return informationClient(model, null);
    }

    @GetMapping("/authorized/client/addSum")
    public String addSum(Model model, Authentication authentication) {
        model.addAttribute("client", clientService.getClientList(authentication.getName()));
        model.addAttribute("clientAddSum", new Client());
        return "/client/addSum";
    }
    @PostMapping("/authorized/client/addSum")
    public String addSumPost(Model model, Client client, Authentication authentication) {
        model.addAttribute(clientService.addSum(authentication.getName(), client.getAccount().getSum()));
        return "redirect:/authorized/client/account";
    }

    @GetMapping("/authorized/client/transfer")
    public ModelAndView transfer(ModelAndView modelAndView, Authentication authentication) {
        modelAndView.addObject("clientLeft", clientService.getClientList(authentication.getName()));
        modelAndView.addObject("clientRight", clientService.getClientList("admin"));
        modelAndView.addObject("clientSumTransfer", new Client());
        modelAndView.setViewName("/client/transfer");
        return modelAndView;
    }

    @PostMapping("/authorized/client/transfer")
    public ModelAndView transferPost(ModelAndView modelAndView, Authentication authentication, Client client) {
        if (client.getAccount().getSum() > clientService.getClientList(authentication.getName()).get(0).getAccount().getSum()) {
            return transfer(modelAndView.addObject("transferError", "Error Message"), authentication);
        } else {
            modelAndView.addObject(clientService.transfer(authentication.getName(), client.getClientName().substring(client.getClientName().lastIndexOf(':') + 2), client.getAccount().getSum()));
            modelAndView.setViewName("redirect:/authorized");
        }
        return modelAndView;
    }

    @GetMapping("/authorized/client/transferInfo")
    public String transferInfo(Model model, Authentication authentication) {
        model.addAttribute("clientTransferInfo", clientService.transferInfo(authentication.getName()));
        return "/client/transferInfo";
    }

    @GetMapping("/authorized/client/doPayment")
    public ModelAndView doPayment(ModelAndView modelAndView, Authentication authentication) {
        modelAndView.addObject("viewPayment", paymentService.getPaymentList(null, authentication.getName()));
        modelAndView.addObject("clientDoPayment", new Payment());
        modelAndView.setViewName("/client/doPayment");
        return modelAndView;
    }

    @PostMapping("/authorized/client/doPayment")
    public ModelAndView doPaymentPost(ModelAndView modelAndView, Authentication authentication, Payment payment) {
        int paymentSum = Integer.parseInt(payment.getName().substring(payment.getName().indexOf("ма:") + 4, payment.getName().indexOf("Н") - 1));

        if (payment.getSum() != paymentSum) {
            return doPayment(modelAndView.addObject("paymentError", "Error Message"), authentication);
        } else if (payment.getSum() > clientService.getClientList(authentication.getName()).get(0).getAccount().getSum()) {
            return doPayment(modelAndView.addObject("paymentError1", "Error Message"), authentication);
        } else {
            String paymentName = payment.getName().substring(payment.getName().indexOf(":") + 2, payment.getName().indexOf("Д") - 1);
            String purposeName = payment.getName().substring(payment.getName().indexOf("ие:") + 4);
            modelAndView.addObject(paymentService.doPayment(authentication.getName(), "rrr", paymentName, paymentSum, purposeName)); //Разработать метод для поиска юр. лица
            modelAndView.setViewName("redirect:/authorized");
            return modelAndView;
        }
    }

    @GetMapping("/authorized/client/viewPayment")
    public String viewPayment(Model model, Authentication authentication) {
        model.addAttribute("viewPayment", paymentService.getPaymentList(null, authentication.getName()));
        return "/client/viewPayment";
    }
}
