package com.example.bankingsystem.domain.model;

public class Bank {

    private int bankId;
    private String name;
    private String License;

    public Bank() {}

    public Bank(int bankId, String name, String license) {
        this.bankId = bankId;
        this.name = name;
        License = license;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }
}
