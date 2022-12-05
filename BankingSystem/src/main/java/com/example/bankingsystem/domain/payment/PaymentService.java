package com.example.bankingsystem.domain.payment;

import java.util.Date;

public interface PaymentService {

    int createPayment(String personName, String purposeName, Date purposeDate, int purposeSum);

}
