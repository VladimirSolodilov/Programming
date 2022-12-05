package com.example.bankingsystem.domain.payment;

import com.example.bankingsystem.domain.model.Payment;

import java.util.List;

public interface PaymentService {

    int createPayment(int personId, String paymentName, int purposeSum, String purposeName);
    List<Payment> getPaymentList(String personName);


}
