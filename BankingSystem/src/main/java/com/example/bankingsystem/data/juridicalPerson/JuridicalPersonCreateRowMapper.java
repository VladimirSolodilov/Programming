package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JuridicalPersonCreateRowMapper implements RowMapper<JuridicalPerson> {
    @Override
    public JuridicalPerson mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        JuridicalPerson juridicalPerson = new JuridicalPerson();

        juridicalPerson.setJuridicalPersonId(resultSet.getInt("PersonId"));
        juridicalPerson.setBranchId(resultSet.getInt("BranchId"));
        juridicalPerson.setRoleId(resultSet.getInt("RoleId"));
        juridicalPerson.setSurname(resultSet.getString("Surname"));
        juridicalPerson.setName(resultSet.getString("Name"));
        juridicalPerson.setPatronymic(resultSet.getString("Patronymic"));
        juridicalPerson.setOrganizationName(resultSet.getString("OrganizationName"));
        juridicalPerson.setJuridicalPersonName(resultSet.getString("PersonName"));
        juridicalPerson.setPassword(resultSet.getString("Password"));

        return juridicalPerson;
    }

}
