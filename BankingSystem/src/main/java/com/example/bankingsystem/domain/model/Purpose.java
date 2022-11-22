package com.example.bankingsystem.domain.model;

public class Purpose {
    private Payment payment;
    private int purposeId;
    private String purposeName;

    public Purpose() {
    }

    public Purpose(Payment payment, int purposeId, String purposeName) {
        this.payment = payment;
        this.purposeId = purposeId;
        this.purposeName = purposeName;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(int purposeId) {
        this.purposeId = purposeId;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }
}
