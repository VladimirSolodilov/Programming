package com.example.bankingsystem.data.role;


import com.example.bankingsystem.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class RoleStorageDB implements RoleStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> getAllRoles() {
        String sqlQuery = "SELECT NAME FROM ROLE";
        return jdbcTemplate.query(sqlQuery, new RoleRowMapper());
    }

    @Override
    public Collection<Role> getRoleById(int roleId) {
        String sqlQuery = "SELECT NAME FROM ROLE WHERE ROLE.ID = ?";
        Collection<Role> collection = jdbcTemplate.query(sqlQuery, new RoleRowMapper(), roleId);
        return collection;
    }
}
