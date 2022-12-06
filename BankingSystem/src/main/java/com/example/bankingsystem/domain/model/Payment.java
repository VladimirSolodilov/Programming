package com.example.bankingsystem.domain.model;

import java.util.Date;

public class Payment {
    private Client client;
    private int paymentId;
    private int personId;
    private String name;
    private java.sql.Date data;
    private int sum;
    private Purpose purpose;

    public Payment() {}

    public Payment(Client client, int paymentId, int personId, String name, java.sql.Date data, int sum, Purpose purpose) {
        this.client = client;
        this.paymentId = paymentId;
        this.personId = personId;
        this.name = name;
        this.data = data;
        this.sum = sum;
        this.purpose = purpose;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getData() {
        return data;
    }

    public void setData(java.sql.Date data) {
        this.data = data;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
