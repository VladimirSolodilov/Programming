package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPerson.JuridicalPersonService;
import com.example.bankingsystem.domain.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JuridicalPersonService juridicalPersonService;



}
