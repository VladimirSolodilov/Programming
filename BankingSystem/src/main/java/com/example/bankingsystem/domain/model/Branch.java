package com.example.bankingsystem.domain.model;

public class Branch {
    private Bank bank;
    private int branchId;
    private int bankId;
    private String name;
    private String address;
    private String bankIdentificationCode;

    public Branch() {}

    public Branch(Bank bank, int branchId, int bankId, String name, String address, String bankIdentificationCode) {
        this.bank = bank;
        this.branchId = branchId;
        this.bankId = bankId;
        this.name = name;
        this.address = address;
        this.bankIdentificationCode = bankIdentificationCode;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankIdentificationCode() {
        return bankIdentificationCode;
    }

    public void setBankIdentificationCode(String bankIdentificationCode) {
        this.bankIdentificationCode = bankIdentificationCode;
    }
}
