package com.example.bankingsystem.domain.model;

public class Purpose {
    private Payment payment;
    private int purposeId;
    private int paymentId;
    private String purposeName;

    public Purpose() {}

    public Purpose(Payment payment, int purposeId, String purposeName, int paymentId) {
        this.payment = payment;
        this.purposeId = purposeId;
        this.purposeName = purposeName;
        this.paymentId = paymentId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }
}
