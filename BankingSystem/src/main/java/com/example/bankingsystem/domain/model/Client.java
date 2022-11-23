package com.example.bankingsystem.domain.model;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ModelAttribute;

public class Client {

    private Branch branch;

    private int clientId;
    private int branchId;
    private int roleId;
    private String surname;
    private String name;
    private String patronymic;
    private String clientName;
    private String password;
    private int sum;

    public Client() {}

    public Client(Branch branch, int clientId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        this.branch = branch;
        this.clientId = clientId;
        this.branchId = branchId;
        this.roleId = roleId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.clientName = clientName;
        this.password = password;
        this.sum = sum;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
