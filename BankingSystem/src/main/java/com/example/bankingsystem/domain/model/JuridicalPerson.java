package com.example.bankingsystem.domain.model;

import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.beans.JavaBean;

public class JuridicalPerson {
    private Branch branch;

    private Account account;
    private int juridicalPersonId;
    private int branchId;
    private int roleId;
    private String surname;
    private String name;
    private String patronymic;
    private String juridicalPersonName;
    private String organizationName;
    private String password;
    public JuridicalPerson() {}
    public JuridicalPerson(Branch branch, Account account, int juridicalPersonId, int branchId, int roleId, String surname, String name, String patronymic, String juridicalPersonName, String organizationName, String password) {
        this.branch = branch;
        this.account = account;
        this.juridicalPersonId = juridicalPersonId;
        this.branchId = branchId;
        this.roleId = roleId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.juridicalPersonName = juridicalPersonName;
        this.organizationName = organizationName;
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getJuridicalPersonId() {
        return juridicalPersonId;
    }

    public void setJuridicalPersonId(int juridicalPersonId) {
        this.juridicalPersonId = juridicalPersonId;
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

    public String getJuridicalPersonName() {
        return juridicalPersonName;
    }

    public void setJuridicalPersonName(String juridicalPersonName) {
        this.juridicalPersonName = juridicalPersonName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
