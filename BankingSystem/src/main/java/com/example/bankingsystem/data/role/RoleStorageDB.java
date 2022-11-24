package com.example.bankingsystem.data.role;


import com.example.bankingsystem.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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
        String sqlQuery = "SELECT RTRIM (Name) FROM ROLE WHERE ROLE.ID = ?";
        Collection<Role> collection = jdbcTemplate.query(sqlQuery, new RoleRowMapper(), roleId);
        return collection;
    }

    @Override
    public Role getRoleById1(int roleId) {
        String sqlQuery = "SELECT RTRIM(NAME) FROM ROLE WHERE ROLE.ID = ?";
        List<Role> list = jdbcTemplate.query(sqlQuery, new RoleRowMapper(), roleId);
        return list.get(0);
    }
}
