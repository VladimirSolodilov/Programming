package com.example.bankingsystem.domain.payment;

import com.example.bankingsystem.domain.model.Payment;

import java.util.List;

public interface PaymentService {
    boolean createPayment(int personId, String clientName, String paymentName, int purposeSum, String purposeName);
    List<Payment> getPaymentList(String personName, String clientName);
    boolean doPayment(String clientName, String personName, String paymentName, int sum, String purposeName);
}
