package com.example.bankingsystem.data.admin;

import com.example.bankingsystem.domain.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminStorageDb implements AdminStorage{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Admin> getAllAdmin(String pattern) {
        StringBuilder getAllAdmin = new StringBuilder("SELECT * from Admin ");
        List<Admin> admins;

        if (pattern != null) {
            getAllAdmin.append(" WHERE Admin.Name LIKE ?");
            admins = jdbcTemplate.query(getAllAdmin.toString(), new AdminRowMapper(), pattern);
        } else {
            admins = jdbcTemplate.query(getAllAdmin.toString(), new AdminRowMapper());
        }

        return admins;
    }
}
