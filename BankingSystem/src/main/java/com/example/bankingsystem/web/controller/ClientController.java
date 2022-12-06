package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.branch.BranchService;
import com.example.bankingsystem.domain.client.ClientService;
import com.example.bankingsystem.domain.model.Branch;
import com.example.bankingsystem.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
        /*if (value == "error") {
            modelAndView.addObject("clientNameError", "Пользователь существует!");
        }*/
        return modelAndView;
    }

    @PostMapping("/client/signUp")
    public String clientRegistrationPost(Client client, Model model, BindingResult bindingResult, Branch branch) {
        /*if (!client.getPassword().equals(client.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/client/registration";
        }
        if (!clientService.saveClient(client)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/client/registration";
        }*/
        //client.setRoles(Collections.singleton(roleStorage.getRoleById1(client.getRoleId())));

        List<Branch> branchList = branchService.getBranchIdByName(branch.getBranchName());

        model.addAttribute(clientService.setClientList(branchList.get(0).getBranchId(), 2, client.getSurname(),
                client.getName(), client.getPatronymic(), client.getClientName(), client.getPassword(), 0));

        return "redirect:/";
    }

    @GetMapping("/authorized/client/removal")
    public ModelAndView clientRemoval(ModelAndView modelAndView) {
        modelAndView.addObject("removal", new Client());
        modelAndView.setViewName("/client/removal");
        return modelAndView;
    }

    @PostMapping("/authorized/client/removal")
    public String deleteMailPost(Model model, Client client) {
        model.addAttribute(clientService.deleteClientList(client.getSurname()));
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
        model.addAttribute(clientService.addSum(authentication.getName(), client.getSum()));
        return "redirect:/authorized/client/account";
    }

    @GetMapping("/authorized/client/transfer")
    public String transfer(Model model, Authentication authentication) {
        model.addAttribute("clientLeft", clientService.getClientList(authentication.getName()));
        model.addAttribute("clientRight", clientService.getClientList("admin"));
        return "/client/transfer";
    }

    @PostMapping("/authorized/client/transfer")
    public String transferPost(Model model, Authentication authentication, Client client) {
        model.addAttribute(clientService.transfer(authentication.getName(), client.getName(), client.getSum()));
        return "redirect:/authorized/client";
    }

    @GetMapping("/authorized/client/transferInfo")
    public String transferInfo(Model model, Authentication authentication) {
        model.addAttribute("clientTransferInfo", clientService.transferInfo(authentication.getName()));
        return "/client/transferInfo";
    }
}
