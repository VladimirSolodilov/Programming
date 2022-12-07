package com.example.bankingsystem.domain.client;

import com.example.bankingsystem.data.admin.AdminStorage;
import com.example.bankingsystem.data.client.ClientStorage;
import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonStorage;
import com.example.bankingsystem.data.role.RoleStorage;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceDomain implements ClientService {
    @Autowired
    private ClientStorage clientStorage;

    @Autowired
    private JuridicalPersonStorage juridicalPersonStorage;
    @Autowired
    private RoleStorage roleStorage;

    @Autowired
    private AdminStorage adminStorage;

    @Autowired
    private TransferStorage transferStorage;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Client> getUserByClientName(String clientName) throws UsernameNotFoundException {
        List<Client> clients = clientStorage.getAllClient(clientName);
        if (clients == null) {
            throw new UsernameNotFoundException("Client not found");
        }
        return clients;
    }

    /*public Client findUserById(int clientId) {
        Optional<Client> client = clientStorage.findById(clientId);
        return ;
    }*/

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
    public List<Transfer> transferInfo(String userName) {
        return transferStorage.viewTransferInfo(userName);
    }
}
