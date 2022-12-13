package com.example.bankingsystem.data.bank;

import com.example.bankingsystem.domain.model.Bank;

import java.util.List;

public interface BankStorage {
    List<Bank> getAllBank();
    int changeInformation(String name, String address, String license);
}
