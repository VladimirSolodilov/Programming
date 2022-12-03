package com.example.bankingsystem.data.admin;

import com.example.bankingsystem.data.client.ClientRowMapper;
import com.example.bankingsystem.domain.model.Admin;
import com.example.bankingsystem.domain.model.Client;
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
        List<Admin> adminList;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from Admin ");

        if (pattern != null) {
            sqlQuery.append(" WHERE Admin.Name LIKE ?");
            adminList = jdbcTemplate.query(sqlQuery.toString(), new AdminRowMapper(), pattern);
        } else {
            adminList = jdbcTemplate.query(sqlQuery.toString(), new AdminRowMapper());
        }

        return adminList;
    }
    @Override
    public List<Admin> findById(int adminId) {
        String sqlQuery = "SELECT Name from Role where Role.RoleId = ?";
        return jdbcTemplate.query(sqlQuery, new AdminRowMapper(), adminId);
    }
}
