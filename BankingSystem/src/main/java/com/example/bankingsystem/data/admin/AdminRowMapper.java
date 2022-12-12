package com.example.bankingsystem.data.admin;

import com.example.bankingsystem.domain.model.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin();

        admin.setAdminId(rs.getInt("AdminId"));
        admin.setName(rs.getString("Name"));
        admin.setPassword(rs.getString("Password"));
        admin.setRoleId(rs.getInt("RoleId"));

        return admin;
    }
}
