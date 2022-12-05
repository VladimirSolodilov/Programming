package com.example.bankingsystem.data.purpose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class PurposeStorageDB implements PurposeStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;


}
