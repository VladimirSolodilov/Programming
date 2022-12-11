package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.admin.AdminService;
import com.example.bankingsystem.domain.client.ClientService;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private JuridicalPersonService juridicalPersonService;

    @GetMapping("/authorized/admin/account")
    public String informationAdmin(Model model, Authentication authentication) {
        model.addAttribute("admin", adminService.getAdminList(authentication.getName()));
        model.addAttribute("title", "Личный кабинет администратора");
        return "/admin/account";
    }

    @GetMapping("/authorized/admin/personList")
    public String personList(Model model, Authentication authentication) {
        model.addAttribute("personList", juridicalPersonService.getPersonList("admin"));
        model.addAttribute("person", new JuridicalPerson());
        return "/admin/personList";
    }

    @GetMapping("/authorized/admin/clientList")
    public String clientList(Model model, Authentication authentication) {
        model.addAttribute("clientList", clientService.getClientList("admin"));
        model.addAttribute("client", new Client());
        return "/admin/clientList";
    }

    @PostMapping("/authorized/admin/clientRemove")
    public String clientRemove(Model model, Client client) {
        System.out.println("ClientName = " + client.getClientName());
        model.addAttribute(clientService.deleteClient(client.getClientName()));
        return "redirect:/authorized/admin/clientList";
    }
    @PostMapping("/authorized/admin/personRemove")
    public String personRemove(Model model, JuridicalPerson juridicalPerson) {
        model.addAttribute(juridicalPersonService.deletePersonList(juridicalPerson.getJuridicalPersonName()));
        return "redirect:/authorized";
    }

}
