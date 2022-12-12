package com.example.bankingsystem.domain.client;

import com.example.bankingsystem.data.client.ClientStorage;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceDomain implements ClientService {
    @Autowired
    private ClientStorage clientStorage;
    @Autowired
    private TransferStorage transferStorage;
    @Lazy
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<Client> getClientList(String clientName) {
        return clientStorage.getAllClient(clientName);
    }

    @Override
    public boolean createClient(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        return clientStorage.createClient(branchId, roleId, surname, name, patronymic, clientName, bCryptPasswordEncoder.encode(password), sum);
    }

    @Override
    public boolean deleteClient(String surname) {
        return clientStorage.deleteClient(surname);
    }

    @Override
    public boolean addSum(String clientName, int sum) {
        return clientStorage.addSum(clientName, sum);
    }

    @Override
    public boolean transfer(String leftClientName, String rightClientName, int sum) {
        return clientStorage.transfer(leftClientName, rightClientName, sum);
    }
    @Override
    public boolean clientChange(String surname, String name, String patronymic, String clientName) {
        return clientStorage.changeClient(surname, name, patronymic, clientName);
    }

    @Override
    public List<Transfer> transferInfo(String userName) {
        return transferStorage.viewTransferInfo(userName);
    }
}
