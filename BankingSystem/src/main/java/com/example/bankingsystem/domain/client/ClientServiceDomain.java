package com.example.bankingsystem.domain.client;

import com.example.bankingsystem.data.admin.AdminStorage;
import com.example.bankingsystem.data.client.ClientStorage;
import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonStorage;
import com.example.bankingsystem.data.role.RoleStorage;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceDomain implements ClientService, UserDetailsService {
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

    public boolean saveClient(Client client) {
        List<Client> clientFromDB = clientStorage.getAllClient(client.getClientName());

        if (clientFromDB != null) {
            return false;
        }

        client.setRoles(Collections.singleton(new Role(2, "CLIENT")));
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientStorage.save(client);
        return true;
    }

    /*public boolean deleteClient(int clientId) {
        if (clientStorage.findById(clientId).isPresent()) {
            clientStorage.deleteClient(clientId);
            return true;
        }
        return false;
    }*/


    @Override
    public int setClientList(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        return clientStorage.setClient(branchId, roleId, surname, name, patronymic, clientName, bCryptPasswordEncoder.encode(password), sum);
    }

    @Override
    public int deleteClientList(String surname) {
        return clientStorage.deleteClient(surname);
    }

    @Override
    public int addSum(String clientName, int sum) {
        return clientStorage.addSum(clientName, sum);
    }

    @Override
    public int transfer(String leftClientName, String rightClientName, int sum) {
        return 0;
    }

    @Override
    public List<Transfer> transferInfo(String userName) {
        return transferStorage.transferInfo(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        List<Client> clientList = clientStorage.getAllClient(s);
        List<JuridicalPerson> personList = juridicalPersonStorage.getAllPerson(s);
        List<Admin> adminList = adminStorage.getAllAdmin(s);

        /*if (clientList.isEmpty()) {
            if (!personList.isEmpty()) {
                JuridicalPerson juridicalPerson = personList.get(0);
                System.out.println(juridicalPerson.getJuridicalPersonName() + ":" + juridicalPerson.getPassword() + " - " + juridicalPerson.getRoleId());
                System.out.println("Role = " + mapRolesToAuthorities(roleStorage.getRoleById(juridicalPerson.getRoleId())));
                return new User(juridicalPerson.getJuridicalPersonName(), juridicalPerson.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(juridicalPerson.getRoleId())));
            }
        } else {
            Client client = clientList.get(0);
            System.out.println(client.getClientName() + ":" + client.getPassword() + " - " + client.getRoleId());
            System.out.println("Role = " + mapRolesToAuthorities(roleStorage.getRoleById(client.getRoleId())));
            return new User(client.getClientName(), client.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(client.getRoleId())));
        }*/

        if (!clientList.isEmpty()) {
            Client client = clientList.get(0);
            System.out.println(client.getClientName() + ":" + client.getPassword() + " - " + client.getRoleId());
            System.out.println("Role = " + mapRolesToAuthorities(roleStorage.getRoleById(client.getRoleId())));
            return new User(client.getClientName(), client.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(client.getRoleId())));
        } else if (!personList.isEmpty()) {
            JuridicalPerson juridicalPerson = personList.get(0);
            System.out.println(juridicalPerson.getJuridicalPersonName() + ":" + juridicalPerson.getPassword() + " - " + juridicalPerson.getRoleId());
            System.out.println("Role = " + mapRolesToAuthorities(roleStorage.getRoleById(juridicalPerson.getRoleId())));
            return new User(juridicalPerson.getJuridicalPersonName(), juridicalPerson.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(juridicalPerson.getRoleId())));
        } else if (!adminList.isEmpty()) {
            Admin admin = adminList.get(0);
            System.out.println(admin.getName() + ":" + admin.getPassword() + " - " + admin.getRoleId());
            System.out.println("Role = " + mapRolesToAuthorities(roleStorage.getRoleById(admin.getRoleId())));
            //System.out.println("Password = " + bCryptPasswordEncoder.encode(admin.getPassword()));
            return new User(admin.getName(), admin.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(admin.getRoleId())));
        }

        throw new InternalAuthenticationServiceException("User not found!");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roleById) {
        return roleById.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
