package com.example.bankingsystem.data.transfer;


import com.example.bankingsystem.domain.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransferStorageDB implements TransferStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String getTransfer = "Select * from Transfer Where Transfer.LeftUser Like ?";
    String createTransfer = "INSERT Transfer Values(?, ?, ?)";
    @Override
    public List<Transfer> viewTransferInfo(String clientName) {
        return jdbcTemplate.query(getTransfer, new TransferRowMapper(), clientName);
    }
    @Override
    public void setTransferInfo(String leftUser, String rightUser, int sum) {
        jdbcTemplate.update(createTransfer, leftUser, rightUser, sum);
    }
}
