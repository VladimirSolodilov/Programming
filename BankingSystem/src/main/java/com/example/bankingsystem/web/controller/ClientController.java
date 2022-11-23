package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.client.ClientService;
import com.example.bankingsystem.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    private final Client client = new Client();

    @GetMapping("/main/client/information")
    public String informationClient(Model model) {
        model.addAttribute("client", clientService.getClientList());
        model.addAttribute("title", "Личный кабинет клиента");
        return "/client/information";
    }

    @GetMapping("/client/registration")
    public ModelAndView clientRegistration(ModelAndView modelAndView) {
        modelAndView.addObject("clientRegistration", new Client());
        modelAndView.setViewName("/client/registration");
        return modelAndView;
    }

    @PostMapping("/client/registration")
    public String clientRegistrationPost(Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/client/registration";
        }
        if (!client.getPassword().equals(client.getPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "/client/registration";
        }
        if (!clientService.saveClient(client)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/client/registration";
        }
        model.addAttribute(clientService.setClientList(client.getClientId(), client.getBranchId(), client.getRoleId(), client.getSurname(),
                client.getName(), client.getPatronymic(), client.getClientName(), client.getPassword(), client.getSum()));
        return informationClient(model);
    }

    @GetMapping("/client/removal")
    public ModelAndView clientRemoval(ModelAndView modelAndView) {
        modelAndView.addObject("removal", new Client());
        modelAndView.setViewName("/client/removal");
        return modelAndView;
    }

    @PostMapping("/client/removal")
    public String deleteMailPost(Model model) {
        model.addAttribute(clientService.deleteClientList(client.getSurname()));
        return informationClient(model);
    }
}
