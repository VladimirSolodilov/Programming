package com.example.bankingsystem.domain.payment;

import com.example.bankingsystem.domain.model.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    int createPayment(int personId, String clientName, String paymentName, int purposeSum, String purposeName);
    List<Payment> getPaymentList(String personName, String clientName);
    int doPayment(String clientName, String personName, String paymentName,int sum, String purposeName);
}
