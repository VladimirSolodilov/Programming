package com.example.bankingsystem.data.bank;

import com.example.bankingsystem.domain.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankStorageDB implements BankStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String getAllBank = "Select * From Bank";
    String changeInformation = "Update Bank Set Name = ?, Address = ?, License = ?";
    @Override
    public List<Bank> getAllBank() {
        return jdbcTemplate.query(getAllBank, new BankRowMapper());
    }
    @Override
    public int changeInformation(String name, String address, String license) {
        return jdbcTemplate.update(changeInformation, name, address, license);
    }
}
