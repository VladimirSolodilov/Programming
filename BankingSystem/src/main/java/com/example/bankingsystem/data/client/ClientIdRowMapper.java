package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientIdRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet resultSet, int i) throws SQLException {
        Client clientModel = new Client();
        clientModel.setClientId(resultSet.getInt("ClientId"));
        return clientModel;
    }

}
