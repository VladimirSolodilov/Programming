package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JuridicalPersonRowMapper implements RowMapper<JuridicalPerson> {

    @Override
    public JuridicalPerson mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        JuridicalPerson personModel = new JuridicalPerson();

        personModel.setJuridicalPersonId(resultSet.getInt("PersonId"));
        personModel.setBranchId(resultSet.getInt("BranchId"));
        personModel.setRoleId(resultSet.getInt("RoleId"));
        personModel.setSurname(resultSet.getString("Surname"));
        personModel.setName(resultSet.getString("Name"));
        personModel.setPatronymic(resultSet.getString("Patronymic"));
        personModel.setJuridicalPersonName(resultSet.getString("PersonName"));
        personModel.setPassword(resultSet.getString("Password"));
        personModel.setSum(resultSet.getInt("Sum"));

        return personModel;
    }
}
