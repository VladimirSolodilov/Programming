package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client clientModel = new Client();

        clientModel.setClientId(resultSet.getInt("client_id"));
        clientModel.setBranchId(resultSet.getInt("client_branchId"));
        clientModel.setRoleId(resultSet.getInt("client_roleId"));
        clientModel.setSurname(resultSet.getString("client_surname"));
        clientModel.setName(resultSet.getString("client_name"));
        clientModel.setPatronymic(resultSet.getString("client_patronymic"));
        clientModel.setClientName(resultSet.getString("client_clientName"));
        clientModel.setPassword(resultSet.getString("client_password"));
        clientModel.setSum(resultSet.getInt("client_sum"));

        return clientModel;
    }

}
