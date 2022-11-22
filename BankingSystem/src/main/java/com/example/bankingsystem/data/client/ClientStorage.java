package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;

import java.util.List;

public interface ClientStorage {

    List<Client> getAllClient(String pattern);
    int setClient (int clientId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    int deleteClient (String surname);


}
