package com.example.bankingsystem.data.account;

import com.example.bankingsystem.domain.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {

        Account accountModel = new Account();

        accountModel.setAccountId(rs.getInt("account_id"));
        accountModel.setUserId(rs.getInt("account_userId"));
        accountModel.setBranchId(rs.getInt("account_branchId"));
        accountModel.setOrganisationName(rs.getString("account_organisationName"));


        return accountModel;
    }
}
