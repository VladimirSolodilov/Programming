package com.example.bankingsystem.domain.clientService;

import com.example.bankingsystem.data.client.ClientStorage;
import com.example.bankingsystem.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClientServiceDomain implements ClientService {


    @Autowired
    private ClientStorage clientStorage;

    @Override
    public List<Client> getClientList() {
        return clientStorage.getAllClient(null);
    }

    @Override
    public List<Client> getUserBySurnameLike(String surname) {
        return clientStorage.getAllClient(surname);
    }

    @Override
    public int setClientList(int clientId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        return clientStorage.setClient(clientId, branchId, roleId, surname, name, patronymic, clientName, password, sum);    }

    @Override
    public int deleteClientList(String surname) {
        return clientStorage.deleteClient(surname);
    }
}
