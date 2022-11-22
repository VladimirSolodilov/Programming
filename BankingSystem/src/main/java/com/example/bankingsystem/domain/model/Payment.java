package com.example.bankingsystem.domain.model;

import lombok.Data;

public class Payment {
    private Account account;
    private int paymentId;
    private int purposeId;
    private Data data;
    private int sum;

    public Payment() {}

    public Payment(Account account, int paymentId, int purposeId, Data data, int sum) {
        this.account = account;
        this.paymentId = paymentId;
        this.purposeId = purposeId;
        this.data = data;
        this.sum = sum;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(int purposeId) {
        this.purposeId = purposeId;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
