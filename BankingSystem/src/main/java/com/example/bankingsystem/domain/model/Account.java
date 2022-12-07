package com.example.bankingsystem.domain.model;

public class Account {

    private Client client;
    private JuridicalPerson juridicalPerson;
    private int accountId;
    private int personId;
    private int clientId;
    private int sum;

    public Account() {
    }

    public Account(Client client, JuridicalPerson juridicalPerson, int accountId, int personId, int clientId, int sum) {
        this.client = client;
        this.juridicalPerson = juridicalPerson;
        this.accountId = accountId;
        this.personId = personId;
        this.clientId = clientId;
        this.sum = sum;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
