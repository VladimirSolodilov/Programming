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

    @GetMapping("/authorized/admin/personList")
    public String personList(Model model) {
        model.addAttribute("personList", juridicalPersonService.getPersonList("admin"));
        model.addAttribute("person", new JuridicalPerson());
        return "/admin/personList";
    }

    @GetMapping("/authorized/admin/clientList")
    public String clientList(Model model) {
        model.addAttribute("clientList", clientService.getClientList("admin"));
        model.addAttribute("client", new Client());
        return "/admin/clientList";
    }

    @PostMapping("/authorized/admin/clientRemove")
    public String clientRemove(Model model, Client client) {
        model.addAttribute(clientService.deleteClient(client.getClientName()));
        return "redirect:/authorized/admin/clientList";
    }
    @PostMapping("/authorized/admin/personRemove")
    public String personRemove(Model model, JuridicalPerson juridicalPerson) {
        model.addAttribute(juridicalPersonService.deletePersonList(juridicalPerson.getJuridicalPersonName()));
        return "redirect:/authorized/admin/personList";
    }
    @PostMapping("/authorized/admin/getClient")
    public String getClient(Model model, Client client) {
        model.addAttribute("client", client);
        model.addAttribute("clientChange", new Client());
        return "/admin/clientChange";
    }

    @PostMapping("/authorized/admin/getPerson")
    public String getPerson(Model model, JuridicalPerson juridicalPerson) {
        model.addAttribute("person", juridicalPerson);
        model.addAttribute("juridicalPersonChange", new JuridicalPerson());
        return "/admin/personChange";
    }

    @PostMapping("/authorized/admin/clientChange")
    public String clientChange(Model model, Client client) {
        model.addAttribute(clientService.clientChange(client.getSurname(), client.getName(), client.getPatronymic(), client.getClientName()));
        return "redirect:/authorized/admin/clientList";
    }

    @PostMapping("/authorized/admin/personChange")
    public String personChange(Model model, JuridicalPerson person) {
        model.addAttribute(juridicalPersonService.personChange(person.getSurname(), person.getName(), person.getPatronymic(),
                person.getOrganizationName(), person.getJuridicalPersonName()));
        return "redirect:/authorized/admin/personList";
    }
    @GetMapping("/authorized/admin/transferInfo")
    public String personTransferInfo(Model model) {
        model.addAttribute("clientTransferInfo", juridicalPersonService.transferInfo(null));
        return "/client/transferInfo";
    }
}
