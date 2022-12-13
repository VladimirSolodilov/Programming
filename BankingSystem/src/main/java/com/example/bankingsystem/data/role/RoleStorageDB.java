package com.example.bankingsystem.data.role;


import com.example.bankingsystem.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public class RoleStorageDB implements RoleStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String getAllRoles = "SELECT Name FROM Role";
    String getRoleById = "SELECT RTRIM (Name) FROM ROLE WHERE ROLE.ID = ?";

    @Override
    public List<Role> getAllRoles() {
        return jdbcTemplate.query(getAllRoles, new RoleRowMapper());
    }

    @Override
    public Collection<Role> getRoleById(int roleId) {
        Collection<Role> collection = jdbcTemplate.query(getRoleById, new RoleRowMapper(), roleId);
        return collection;
    }

    @Override
    public Role getRoleById1(int roleId) {
        List<Role> list = jdbcTemplate.query(getRoleById, new RoleRowMapper(), roleId);
        return list.get(0);
    }
}
