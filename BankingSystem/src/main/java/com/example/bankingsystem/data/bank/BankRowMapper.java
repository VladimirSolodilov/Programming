package com.example.bankingsystem.data.bank;

import com.example.bankingsystem.domain.model.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRowMapper implements RowMapper<Bank> {

    @Override
    public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bank bankModel = new Bank();

        bankModel.setBankId(rs.getInt("BankId"));
        bankModel.setName(rs.getString("Name"));
        bankModel.setLicense(rs.getString("License"));
        bankModel.setAddress(rs.getString("Address"));

        return bankModel;
    }
}
