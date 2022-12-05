package com.example.bankingsystem.data.transfer;

import com.example.bankingsystem.domain.model.Role;
import com.example.bankingsystem.domain.model.Transfer;

import java.util.Collection;
import java.util.List;

public interface TransferStorage {
    List<Transfer> transferInfo(String clientName);
}