package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JuridicalPersonRowMapper implements RowMapper<JuridicalPerson> {

    @Override
    public JuridicalPerson mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        JuridicalPerson personModel = new JuridicalPerson();

        personModel.setJuridicalPersonId(resultSet.getInt("juridicalPerson_id"));
        personModel.setBranchId(resultSet.getInt("juridicalPerson_branchId"));
        personModel.setRoleId(resultSet.getInt("juridicalPerson_roleId"));
        personModel.setSurname(resultSet.getString("juridicalPerson_surname"));
        personModel.setName(resultSet.getString("juridicalPerson_name"));
        personModel.setPatronymic(resultSet.getString("juridicalPerson_patronymic"));
        personModel.setJuridicalPersonName(resultSet.getString("juridicalPerson_JuridicalPersonName"));
        personModel.setPassword(resultSet.getString("juridicalPerson_password"));
        personModel.setSum(resultSet.getInt("juridicalPerson_sum"));

        return personModel;
    }
}
