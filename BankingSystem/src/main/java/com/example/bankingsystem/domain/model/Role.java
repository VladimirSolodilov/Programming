package com.example.bankingsystem.domain.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class Role implements GrantedAuthority {

    private int roleId;
    private String name;
    private Set<Client> clients;
    private Set<JuridicalPerson> juridicalPersons;

    public Role() {
    }

    public Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<JuridicalPerson> getJuridicalPersons() {
        return juridicalPersons;
    }

    public void setJuridicalPersons(Set<JuridicalPerson> juridicalPersons) {
        this.juridicalPersons = juridicalPersons;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
