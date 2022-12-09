package com.example.bankingsystem.domain.model;

import java.util.Set;

public class Client {
    private Branch branch;
    private Account account;
    private int clientId;
    private int branchId;
    private int roleId;
    private String surname;
    private String name;
    private String patronymic;
    private String clientName;
    private String password;
    private Set<Role> roles;
    public Client() {}

    public Client(Branch branch, Account account, int clientId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, Set<Role> roles) {
        this.branch = branch;
        this.account = account;
        this.clientId = clientId;
        this.branchId = branchId;
        this.roleId = roleId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.clientName = clientName;
        this.password = password;
        this.roles = roles;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
