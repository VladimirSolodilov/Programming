package com.example.bankingsystem.domain.model;

public class AccountRequisites {
    private Account account;
    private int requisitesId;
    private String personalAccount;
    private String paymentAccount;

    public AccountRequisites() {
    }

    public AccountRequisites(Account account, int requisitesId, String personalAccount, String paymentAccount) {
        this.account = account;
        this.requisitesId = requisitesId;
        this.personalAccount = personalAccount;
        this.paymentAccount = paymentAccount;
    }

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

    public String getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(String personalAccount) {
        this.personalAccount = personalAccount;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }
}
