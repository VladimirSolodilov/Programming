package com.example.bankingsystem.data.payment;

import java.util.Date;

public interface PaymentStorage {

    int createPayment(String personName, String purposeName, Date purposeDate, int purposeSum);

}
