package com.example.bankingsystem.domain.model;

public class AccountRequisites {
    private Account account;
    private int requisitesId;
    private int personalAccount;
    private int paymentAccount;

    public AccountRequisites() {}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getRequisitesId() {
        return requisitesId;
    }

    public void setRequisitesId(int requisitesId) {
        this.requisitesId = requisitesId;
    }

    public int getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(int personalAccount) {
        this.personalAccount = personalAccount;
    }

    public int getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(int paymentAccount) {
        this.paymentAccount = paymentAccount;
    }
}
