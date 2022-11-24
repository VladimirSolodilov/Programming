package com.example.bankingsystem.data.role;

import com.example.bankingsystem.domain.model.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setName(resultSet.getString("Name"));
        return role;
    }
}
