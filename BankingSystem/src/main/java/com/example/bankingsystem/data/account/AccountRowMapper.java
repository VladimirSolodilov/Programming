package com.example.bankingsystem.data.account;

import com.example.bankingsystem.domain.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

        Account account = new Account();

        account.setAccountId(rs.getInt("AccountId"));
        account.setPersonId(rs.getInt("PersonId"));
        account.setClientId(rs.getInt("ClientId"));
        account.setSum(rs.getInt("Sum"));

        return account;
    }
}
