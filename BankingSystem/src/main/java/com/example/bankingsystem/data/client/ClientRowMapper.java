package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client clientModel = new Client();

        clientModel.setClientId(resultSet.getInt("ClientId"));
        clientModel.setBranchId(resultSet.getInt("BranchId"));
        clientModel.setRoleId(resultSet.getInt("RoleId"));
        clientModel.setSurname(resultSet.getString("Surname"));
        clientModel.setName(resultSet.getString("Name"));
        clientModel.setPatronymic(resultSet.getString("Patronymic"));
        clientModel.setClientName(resultSet.getString("clientName"));
        clientModel.setPassword(resultSet.getString("Password"));
        clientModel.setSum(resultSet.getInt("Sum"));

        return clientModel;
    }

}
