package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.data.role.RoleStorage;
import com.example.bankingsystem.domain.client.ClientService;
import com.example.bankingsystem.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoleStorage roleStorage;

    @GetMapping("/authorized/client/account")
    public String informationClient(Model model) {
        model.addAttribute("client", clientService.getClientList());
        model.addAttribute("title", "Личный кабинет клиента");
        return "/client/account";
    }

    @GetMapping("/client/signUp")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("clientSignUp", new Client());
        modelAndView.setViewName("/client/clientSignUp");
        return modelAndView;
    }

    @PostMapping("/client/signUp")
    public String clientRegistrationPost(Model model, Client client) {
        /*if (!client.getPassword().equals(client.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/client/registration";
        }
        if (!clientService.saveClient(client)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/client/registration";
        }*/
        //client.setRoles(Collections.singleton(roleStorage.getRoleById1(client.getRoleId())));
        model.addAttribute(clientService.setClientList(1, 2, client.getSurname(),
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
        return informationClient(model);
    }
}
