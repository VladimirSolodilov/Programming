package com.example.bankingsystem.data.transfer;


import com.example.bankingsystem.data.client.ClientStorage;
import com.example.bankingsystem.domain.model.Role;
import com.example.bankingsystem.domain.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class TransferStorageDB implements TransferStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Transfer> viewTransferInfo(String clientName) {
        String getTransfer = "Select * from Transfer Where Transfer.LeftUser Like ?";
        return jdbcTemplate.query(getTransfer, new TransferRowMapper(), clientName);
    }
    @Override
    public boolean setTransferInfo(String leftUser, String rightUser, int sum) {
        String createTransfer = "INSERT Transfer Values(?, ?, ?)";
        jdbcTemplate.update(createTransfer, leftUser, rightUser, sum);
        return true;
    }
}
