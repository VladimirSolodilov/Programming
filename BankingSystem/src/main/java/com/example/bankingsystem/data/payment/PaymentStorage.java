package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.domain.model.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentStorage {
    List<Payment> getPaymentList(String personName, String clientName);
    boolean createPayment(int personId, String clientName,String paymentName, int paymentSum, String purposeName);
    boolean doPayment(String clientName, String personName, String paymentName, int sum, String purposeName);

}
