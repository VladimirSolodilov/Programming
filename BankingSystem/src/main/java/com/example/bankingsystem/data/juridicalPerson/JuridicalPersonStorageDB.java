package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.data.client.ClientRowMapper;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class JuridicalPersonStorageDB implements JuridicalPersonStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JuridicalPerson> getAllPerson(String pattern) {
        List<JuridicalPerson> juridicalPersonList;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from JuridicalPerson ");

        if (!Objects.equals(pattern, "admin")) {
            sqlQuery.append(" WHERE JuridicalPerson.PersonName LIKE ?");
            juridicalPersonList = jdbcTemplate.query(sqlQuery.toString(), new JuridicalPersonRowMapper(), pattern);
        } else {
            juridicalPersonList = jdbcTemplate.query(sqlQuery.toString(), new JuridicalPersonRowMapper());
        }

        return juridicalPersonList;
    }

    @Override
    public int setJuridicalPerson(int branchId, int roleId, String surname, String name, String patronymic, String JuridicalPersonName, String password, int sum) {
        String sqlQuery = "INSERT into JuridicalPerson VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, branchId, roleId, surname, name, patronymic, JuridicalPersonName, password, sum);
    }

    @Override
    public int deleteJuridicalPerson(String surname) {
        String sqlQuery = "DELETE from JuricalPerson where JuridicalPerson.Surname LIKE ?";
        return jdbcTemplate.update(sqlQuery, surname);
    }

    @Override
    public int addSum(String personName, int sum) {
        String sqlQuery = "Update JuridicalPerson Set JuridicalPerson.Sum = JuridicalPerson.Sum + ? where JuridicalPerson.PersonName Like ?";
        return jdbcTemplate.update(sqlQuery, sum, personName);
    }

    @Override
    public int transfer(String leftPerson, String rightPerson, int sum) {
        String sqlQuery1 = "Update JuridicalPerson Set JuridicalPerson.Sum = JuridicalPerson.Sum - ? Where JuridicalPerson.PersonName Like ?";
        String sqlQuery2 = "Update JuridicalPerson Set JuridicalPerson.Sum = JuridicalPerson.Sum + ? Where JuridicalPerson.PersonName Like ?";
        jdbcTemplate.update(sqlQuery1, new ClientRowMapper(), leftPerson);
        return jdbcTemplate.update(sqlQuery2, new ClientRowMapper(), rightPerson);
    }

    @Override
    public List<JuridicalPerson> getIdByPersonName(String personName) {
        String sqlQuery = "Select PersonId from JuridicalPerson Where PersonName Like ?";
        return jdbcTemplate.query(sqlQuery, new JuridicalPersonRowMapper(), personName);
    }

}
