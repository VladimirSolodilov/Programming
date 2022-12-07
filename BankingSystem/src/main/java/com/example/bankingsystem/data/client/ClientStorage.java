package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;

import java.util.List;

public interface ClientStorage {
    List<Client> getAllClient(String pattern);
    boolean createClient (int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    boolean deleteClient (String surname);

    boolean addSum(String clientName, int sum);

    boolean transfer(String leftClientName, String rightClientName, int sum);

    List<Client> transferInfo(String clientName);
    List<Client> findByClientId(int clientId);
}
