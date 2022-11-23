package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.client.ClientService;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JuridicalPersonService juridicalPersonService;
    private Client client = new Client();
    private JuridicalPerson juridicalPerson = new JuridicalPerson();

    @GetMapping("/admin/client")
    public String clientList(Model model) {
        model.addAttribute("allClients", clientService.getClientList());
        return "/admin/client";
    }

    @PostMapping("/admin/client")
    public String clientRemoval(Model model) {
        model.addAttribute(clientService.deleteClientList(client.getSurname()));
        return clientList(model);
    }

    @GetMapping("/admin/person")
    public String personList(Model model) {
        model.addAttribute("allPersons", juridicalPersonService.getPersonList());
        return "/admin/person";
    }

    @PostMapping("/admin/person")
    public String personRemoval(Model model) {
        model.addAttribute(juridicalPersonService.deletePersonList(juridicalPerson.getSurname()));
        return personList((model));
    }

    @PostMapping("/admin")
    public String  deleteUser(int userId, String action, Model model) {
        if (action.equals("delete")){
            //clientService.deleteClientList(userId);
        }
        return "redirect:/admin";
    }


}
