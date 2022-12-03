package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;

import java.util.List;

public interface ClientStorage {

    List<Client> getAllClient(String pattern);
    int setClient (int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    int deleteClient (String surname);

    int addSum(String userName, int sum);

    int transfer(String leftClientName, String rightClientName, int sum);

    int save(Client client);

    List<Client> findById(int clientId);
}
