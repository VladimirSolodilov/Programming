package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.domain.model.Payment;

import java.util.Date;
import java.util.List;

public interface PaymentStorage {

    int createPayment(int personId, String paymentName, int paymentSum, String purposeName);
    List<Payment> getPaymentList(String personName);


}
