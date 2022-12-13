package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.domain.model.Account;
import com.example.bankingsystem.domain.model.AccountRequisites;
import com.example.bankingsystem.domain.model.Branch;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JuridicalPersonRowMapper implements RowMapper<JuridicalPerson> {
    @Override
    public JuridicalPerson mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        JuridicalPerson juridicalPerson = new JuridicalPerson();
        Account account = new Account();
        AccountRequisites accountRequisites = new AccountRequisites();
        Branch branch = new Branch();

        juridicalPerson.setJuridicalPersonId(resultSet.getInt("PersonId"));
        juridicalPerson.setBranchId(resultSet.getInt("BranchId"));
        juridicalPerson.setRoleId(resultSet.getInt("RoleId"));
        juridicalPerson.setSurname(resultSet.getString("Surname"));
        juridicalPerson.setName(resultSet.getString("Name"));
        juridicalPerson.setPatronymic(resultSet.getString("Patronymic"));
        juridicalPerson.setOrganizationName(resultSet.getString("OrganizationName"));
        juridicalPerson.setJuridicalPersonName(resultSet.getString("PersonName"));
        juridicalPerson.setPassword(resultSet.getString("Password"));

        juridicalPerson.setAccount(account);
        account.setAccountRequisites(accountRequisites);
        juridicalPerson.setBranch(branch);

        account.setSum(resultSet.getInt("Sum"));
        accountRequisites.setPaymentAccount(resultSet.getInt("PaymentAccount"));
        branch.setBranchName(resultSet.getString("BranchName"));

        return juridicalPerson;
    }
}
