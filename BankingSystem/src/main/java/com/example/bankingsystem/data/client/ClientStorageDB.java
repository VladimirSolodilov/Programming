package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public class ClientStorageDB implements ClientStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> getAllClient (String pattern) {
        List<Client> clientList;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from Client ");

        if (pattern != null) {
            sqlQuery.append(" WHERE Client.Surname LIKE ?");
            clientList = jdbcTemplate.query(sqlQuery.toString(), new ClientRowMapper(), pattern);
        } else {
            clientList = jdbcTemplate.query(sqlQuery.toString(), new ClientRowMapper());
        }

        return clientList;
    }

    @Override
    public int setClient(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        String sqlQuery = "INSERT into Client VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, branchId, roleId, surname, name, patronymic, clientName, password, sum);
    }

    @Override
    public int deleteClient(String surname) {
        String sqlQuery = "DELETE from Client where Client.Surname LIKE ?";
        return jdbcTemplate.update(sqlQuery, surname);
    }

    @Override
    public int save(Client client) {
        String sqlQuery = "INSERT into Client VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, client.getBranch(), client.getRoleId(), client.getSurname(),
                client.getName(), client.getPatronymic(), client.getClientName(), client.getPassword(), client.getSum());
    }

    @Override
    public Client findById(int clientId) {
        Client client;

        String sqlQuery = "SELECT * from Client where client.clientId = ?";
        client = (Client) jdbcTemplate.query(sqlQuery, new ClientRowMapper(), clientId);

        return client;
    }
}
