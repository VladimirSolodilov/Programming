package com.example.bankingsystem.domain.model;

public class Account {
    private JuridicalPerson juridicalPerson;
    private Client client;
    private AccountRequisites accountRequisites;
    private int accountId;
    private int personId;
    private int clientId;
    private int sum;
    public Account() {}
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

    public AccountRequisites getAccountRequisites() {
        return accountRequisites;
    }

    public void setAccountRequisites(AccountRequisites accountRequisites) {
        this.accountRequisites = accountRequisites;
    }
}
