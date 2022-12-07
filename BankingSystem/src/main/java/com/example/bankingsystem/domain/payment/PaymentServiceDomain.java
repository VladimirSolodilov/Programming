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
    public boolean createPayment(int personId, String clientName, String paymentName, int paymentSum, String purposeName) {
        return paymentStorage.createPayment(personId, clientName, paymentName, paymentSum, purposeName);
    }

    @Override
    public List<Payment> getPaymentList(String personName, String clientName) {
        return paymentStorage.getPaymentList(personName, clientName);
    }

    @Override
    public boolean doPayment(String clientName, String personName, String paymentName, int sum, String purposeName) {
        return paymentStorage.doPayment(clientName, personName, paymentName, sum, purposeName);
    }
}
