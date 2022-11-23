package com.example.bankingsystem.web.controller;

import com.example.bankingsystem.domain.JuridicalPersonService.JuridicalPersonService;
import com.example.bankingsystem.domain.clientService.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.ClientHttpRequestFactorySupplier;

public class RegistrationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JuridicalPersonService juridicalPersonService;



}
