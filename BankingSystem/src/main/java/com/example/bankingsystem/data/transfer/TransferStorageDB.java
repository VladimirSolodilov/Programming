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
    public List<Transfer> transferInfo(String clientName) {
        String sqlQuery = "Select * from Transfer Where Transfer.LeftUser Like ?";
        return jdbcTemplate.query(sqlQuery, new TransferRowMapper(), clientName);
    }
}
