package com.example.bankingsystem.domain.model;

import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.beans.JavaBean;

public class JuridicalPerson {

    private Branch branch;
    private int juridicalPersonId;
    private int branchId;
    private int roleId;
    private String surname;
    private String name;
    private String patronymic;
    private String juridicalPersonName;
    private String password;
    private int sum;

    public JuridicalPerson() {}

    public JuridicalPerson(Branch branch, int juridicalPersonId, int branchId, int roleId, String surname, String name, String patronymic, String juridicalPersonName, String password, int sum) {
        this.branch = branch;
        this.juridicalPersonId = juridicalPersonId;
        this.branchId = branchId;
        this.roleId = roleId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.juridicalPersonName = juridicalPersonName;
        this.password = password;
        this.sum = sum;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
