package com.example.bankingsystem.domain.payment;

import com.example.bankingsystem.data.payment.PaymentStorage;
import com.example.bankingsystem.domain.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceDomain implements PaymentService {

    @Autowired
    private PaymentStorage paymentStorage;

    @Override
    public int createPayment(int personId, String paymentName, int paymentSum, String purposeName) {
        return paymentStorage.createPayment(personId, paymentName, paymentSum, purposeName);
    }

    @Override
    public List<Payment> getPaymentList(String personName) {
        return paymentStorage.getPaymentList(personName);
    }
}
