package com.example.bankingsystem.data.client;

import com.example.bankingsystem.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Transactional
@Repository
public class ClientStorageDB implements ClientStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> getAllClient (String pattern) {
        List<Client> clientList;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from Client ");

        System.out.println("Pattern = " + pattern);

        if (!Objects.equals(pattern, "admin")) {
            sqlQuery.append(" WHERE Client.ClientName LIKE ?");
            clientList = jdbcTemplate.query(sqlQuery.toString(), new ClientRowMapper(), pattern);
            System.out.println("Operation 1");
        } else {
            clientList = jdbcTemplate.query(sqlQuery.toString(), new ClientRowMapper());
            System.out.println("Operation 2");
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
    public int addSum(String userName, int sum) {
        String sqlQuery = "Update Client Set Client.Sum = Client.Sum + ? where Client.ClientName Like ?";
        return jdbcTemplate.update(sqlQuery, sum, userName);
    }

    @Override
    public int transfer(String leftClientName, String rightClientName, int sum) {
        //List<Client> leftClientList, rightClientList;

        String sqlQuery1 = "Update Client Set Client.Sum = Client.Sum - ? Where Client.ClientName Like ?";
        String sqlQuery2 = "Update Client Set Client.Sum = Client.Sum + ? Where Client.ClientName Like ?";

        jdbcTemplate.update(sqlQuery1, new ClientRowMapper(), leftClientName);
        return jdbcTemplate.update(sqlQuery2, new ClientRowMapper(), rightClientName);

        /*leftClientList = jdbcTemplate.query(sqlQueryLeft, new ClientRowMapper(), leftClientName);
        rightClientList = jdbcTemplate.query(sqlQueryLeft, new ClientRowMapper(), rightClientName);

        Client leftClient = leftClientList.get(0);
        Client rightClient = rightClientList.get(0);*/
    }

    @Override
    public List<Client> transferInfo(String clientName) {
        return null;
    }

    @Override
    public int save(Client client) {
        String sqlQuery = "INSERT into Client VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, client.getBranch(), client.getRoleId(), client.getSurname(),
                client.getName(), client.getPatronymic(), client.getClientName(), client.getPassword(), client.getSum());
    }

    @Override
    public List<Client> findById(int clientId) {
        String sqlQuery = "SELECT Name from Role where Role.RoleId = ?";
        return jdbcTemplate.query(sqlQuery, new ClientRowMapper(), clientId);
    }
}
