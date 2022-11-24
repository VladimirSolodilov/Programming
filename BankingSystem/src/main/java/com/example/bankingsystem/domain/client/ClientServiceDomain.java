package com.example.bankingsystem.domain.client;

import com.example.bankingsystem.data.client.ClientStorage;
import com.example.bankingsystem.data.role.RoleStorage;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RoleStorage roleStorage;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public List<Client> getClientList() {
        return clientStorage.getAllClient(null);
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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<Client> list = clientStorage.getAllClient(s);
        Client client = list.get(0);
        System.out.println(mapRolesToAuthorities(roleStorage.getRoleById(client.getRoleId())));
        return new org.springframework.security.core.userdetails.User(client.getClientName(), client.getPassword(),
                true, true, true, true, mapRolesToAuthorities(roleStorage.getRoleById(client.getRoleId())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roleById) {
        return roleById.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
