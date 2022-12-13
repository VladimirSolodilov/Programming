package com.example.bankingsystem.domain.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class Admin {

    private Bank bank;
    private int adminId;
    private String name;
    private String password;
    private int roleId;

    public Admin() {}

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
