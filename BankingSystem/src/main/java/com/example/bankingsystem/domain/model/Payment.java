package com.example.bankingsystem.domain.model;

import java.util.Date;

public class Payment {
    private int paymentId;
    private int accountId;
    private Date data;
    private int sum;

    public Payment() {}

    public Payment(int paymentId, int accountId, Date data, int sum) {
        this.paymentId = paymentId;
        this.accountId = accountId;
        this.data = data;
        this.sum = sum;
    }
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
