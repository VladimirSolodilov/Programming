package com.example.bankingsystem.domain.bank;

import com.example.bankingsystem.data.bank.BankStorage;
import com.example.bankingsystem.domain.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceDomain implements BankService {
    @Autowired
    private BankStorage bankStorage;
    @Override
    public List<Bank> getAllBanks() {
        return bankStorage.getAllBank();
    }

    @Override
    public int changeInformation(String name, String address, String license) {
        return bankStorage.changeInformation(name, address, license);
    }
}
