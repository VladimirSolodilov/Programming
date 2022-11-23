package com.example.bankingsystem.data.account;

import com.example.bankingsystem.domain.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

        Account accountModel = new Account();

        accountModel.setAccountId(rs.getInt("AccountId"));
        accountModel.setUserId(rs.getInt("UserId"));
        accountModel.setBranchId(rs.getInt("BranchId"));
        accountModel.setOrganisationName(rs.getString("OrganizationName"));
        accountModel.setPaymentNumber(rs.getString("PaymentNumber"));

        return accountModel;
    }
}
