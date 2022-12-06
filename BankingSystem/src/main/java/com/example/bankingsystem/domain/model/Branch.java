package com.example.bankingsystem.domain.model;

public class Branch {
    private Bank bank;
    private int branchId;
    private int bankId;
    private String branchName;
    private String address;
    private String bankIdentificationCode;

    public Branch() {}

    public Branch(Bank bank, int branchId, int bankId, String branchName, String address, String bankIdentificationCode) {
        this.bank = bank;
        this.branchId = branchId;
        this.bankId = bankId;
        this.branchName = branchName;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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
