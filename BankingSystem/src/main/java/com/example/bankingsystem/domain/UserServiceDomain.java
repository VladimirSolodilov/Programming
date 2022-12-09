package com.example.bankingsystem.domain;

import com.example.bankingsystem.data.admin.AdminStorage;
import com.example.bankingsystem.data.client.ClientStorage;
import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonStorage;
import com.example.bankingsystem.data.role.RoleStorage;
import com.example.bankingsystem.domain.model.Admin;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceDomain implements UserDetailsService {
    @Autowired
    private RoleStorage roleStorage;
    @Autowired
    private ClientStorage clientStorage;
    @Autowired
    private JuridicalPersonStorage juridicalPersonStorage;
    @Autowired
    private AdminStorage adminStorage;
    @Override
    public UserDetails loadUserByUsername(String s) {
        List<Client> clientList = clientStorage.getAllClient(s);
        List<JuridicalPerson> personList = juridicalPersonStorage.getAllPerson(s);
        List<Admin> adminList = adminStorage.getAllAdmin(s);

        if (!clientList.isEmpty()) {
            Client client = clientList.get(0);
            return new User(client.getClientName(), client.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(client.getRoleId())));
        } else if (!personList.isEmpty()) {
            JuridicalPerson juridicalPerson = personList.get(0);
            return new User(juridicalPerson.getJuridicalPersonName(), juridicalPerson.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(juridicalPerson.getRoleId())));
        } else if (!adminList.isEmpty()) {
            Admin admin = adminList.get(0);
            return new User(admin.getName(), admin.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(admin.getRoleId())));
        }

        throw new InternalAuthenticationServiceException("User not found!");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roleById) {
        return roleById.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
