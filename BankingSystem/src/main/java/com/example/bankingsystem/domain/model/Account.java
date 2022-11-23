package com.example.bankingsystem.domain.model;

public class Account {

    private Client client;
    private JuridicalPerson juridicalPerson;
    private int accountId;
    private int userId;
    private int branchId;
    private String organisationName;
    private String paymentNumber;

    public Account() {
    }

    public Account(Client client, JuridicalPerson juridicalPerson, int accountId, int userId, int branchId, String organisationName, String paymentNumber) {
        this.client = client;
        this.juridicalPerson = juridicalPerson;
        this.accountId = accountId;
        this.userId = userId;
        this.branchId = branchId;
        this.organisationName = organisationName;
        this.paymentNumber = paymentNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public JuridicalPerson getJuridicalPerson() {
        return juridicalPerson;
    }

    public void setJuridicalPerson(JuridicalPerson juridicalPerson) {
        this.juridicalPerson = juridicalPerson;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }
}
