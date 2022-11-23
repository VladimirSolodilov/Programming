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
        StringBuilder sqlQuery = new StringBuilder("SELECT * from client ");

        if (pattern != null) {
            sqlQuery.append(" WHERE client.client_surname LIKE ?");
            clientList = jdbcTemplate.query(sqlQuery.toString(), new ClientRowMapper(), pattern);
        } else {
            clientList = jdbcTemplate.query(sqlQuery.toString(), new ClientRowMapper());
        }

        return clientList;
    }

    @Override
    public int setClient(int clientId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        String sqlQuery = "INSERT into client VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, branchId, roleId, surname, name, patronymic, clientName, password, sum);
    }

    @Override
    public int deleteClient(String surname) {
        String sqlQuery = "DELETE from client where client.Surname LIKE ?";
        return jdbcTemplate.update(sqlQuery, surname);
    }
}
