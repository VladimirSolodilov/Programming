package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.admin.AdminService;
import com.example.bankingsystem.domain.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("personList", juridicalPersonService.getPersonList(authentication.getName()));
        return "/admin/personList";
    }

    @GetMapping("/authorized/admin/clientList")
    public String clientList(Model model, Authentication authentication) {
        model.addAttribute("clientList", clientService.getClientList(authentication.getName()));
        return "/admin/clientList";
    }
}
