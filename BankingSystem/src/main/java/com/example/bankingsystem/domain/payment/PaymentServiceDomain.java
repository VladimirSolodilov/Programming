package com.example.bankingsystem.domain.payment;

import com.example.bankingsystem.data.payment.PaymentStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class PaymentServiceDomain implements PaymentService {

    @Autowired
    private PaymentStorage paymentStorage;

    @Override
    public int createPayment(String personName, String purposeName, Date purposeDate, int purposeSum) {
        return paymentStorage.createPayment(personName, purposeName, purposeDate, purposeSum);
    }
}
