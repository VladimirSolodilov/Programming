package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientCreateRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client client = new Client();

        client.setClientId(resultSet.getInt("ClientId"));
        client.setBranchId(resultSet.getInt("BranchId"));
        client.setRoleId(resultSet.getInt("RoleId"));
        client.setSurname(resultSet.getString("Surname"));
        client.setName(resultSet.getString("Name"));
        client.setPatronymic(resultSet.getString("Patronymic"));
        client.setClientName(resultSet.getString("ClientName"));
        client.setPassword(resultSet.getString("Password"));

        return client;
    }
}
