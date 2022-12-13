package com.example.bankingsystem.data.transfer;

import com.example.bankingsystem.domain.model.Transfer;

import java.util.List;

public interface TransferStorage {
    List<Transfer> viewTransferInfo(String clientName);
    void setTransferInfo(String leftUser, String rightUser, int sum);
}
