package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class JuridicalPersonStorageDB implements JuridicalPersonStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JuridicalPerson> getAllPerson(String pattern) {
        return null;
    }

    @Override
    public int setJuridicalPerson(int clientId, int branchId, int roleId, String surname, String name, String patronymic, String JuridicalPersonName, String password, int sum) {
        return 0;
    }

    @Override
    public int deleteJuridicalPerson(String surname) {
        return 0;
    }

}
