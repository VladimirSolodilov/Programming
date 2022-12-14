package com.example.bankingsystem.data.role;


import com.example.bankingsystem.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class RoleStorageDB implements RoleStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String getRoleById = "Select RTRIM (Name) From Role Where Role.Id = ?";

    @Override
    public Collection<Role> getRoleByIdCollection(int roleId) {
        return jdbcTemplate.query(getRoleById, new RoleRowMapper(), roleId);
    }
}
