package com.example.bankingsystem.data.accountRequisites;

import com.example.bankingsystem.domain.model.AccountRequisites;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRequisitesRowMapper implements RowMapper<AccountRequisites> {
    @Override
    public AccountRequisites mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountRequisites accountRequisites = new AccountRequisites();

        accountRequisites.setPaymentAccount(rs.getInt("PaymentAccount"));
        accountRequisites.setPersonalAccount(rs.getInt("PersonalAccount"));

        return accountRequisites;
    }
}
