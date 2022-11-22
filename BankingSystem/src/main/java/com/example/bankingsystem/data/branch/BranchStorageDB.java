package com.example.bankingsystem.data.branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BranchStorageDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

}
