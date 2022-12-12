package com.example.bankingsystem.data.bank;

import com.example.bankingsystem.domain.model.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRowMapper implements RowMapper<Bank> {
    @Override
    public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bank bank = new Bank();

        bank.setBankId(rs.getInt("BankId"));
        bank.setName(rs.getString("Name"));
        bank.setLicense(rs.getString("License"));
        bank.setAddress(rs.getString("Address"));

        return bank;
    }
}
