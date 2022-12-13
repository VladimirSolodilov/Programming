package com.example.bankingsystem.data.transfer;


import com.example.bankingsystem.domain.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class TransferStorageDB implements TransferStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    StringBuilder getTransfer = new StringBuilder("Select * from Transfer");
    String createTransfer = "INSERT Transfer Values(?, ?, ?)";
    @Override
    public List<Transfer> viewTransferInfo(String clientName) {
        if (Objects.equals(clientName, null)) {
            return jdbcTemplate.query(getTransfer.toString(), new TransferRowMapper());
        } else {
            getTransfer.append(" Where Transfer.LeftUser Like ?");
            return jdbcTemplate.query(getTransfer.toString(), new TransferRowMapper(), clientName);
        }
    }
    @Override
    public void setTransferInfo(String leftUser, String rightUser, int sum) {
        jdbcTemplate.update(createTransfer, leftUser, rightUser, sum);
    }
}
