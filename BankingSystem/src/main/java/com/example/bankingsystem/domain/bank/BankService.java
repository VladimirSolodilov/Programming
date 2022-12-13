package com.example.bankingsystem.domain.bank;

import com.example.bankingsystem.domain.model.Bank;

import java.util.List;

public interface BankService {
    List<Bank> getAllBanks();
    int changeInformation(String name, String address, String license);
}
