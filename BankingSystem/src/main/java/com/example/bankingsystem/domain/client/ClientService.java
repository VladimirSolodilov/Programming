package com.example.bankingsystem.domain.client;

import com.example.bankingsystem.domain.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getClientList();
    List<Client> getUserBySurnameLike(String surname);

    int setClientList(int clientId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    int deleteClientList(String surname);

}
