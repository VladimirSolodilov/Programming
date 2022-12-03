package com.example.bankingsystem.data.admin;

import com.example.bankingsystem.domain.model.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin adminModel = new Admin();

        adminModel.setAdminId(rs.getInt("AdminId"));
        adminModel.setName(rs.getString("Name"));
        adminModel.setPassword(rs.getString("Password"));;
        adminModel.setRoleId(rs.getInt("RoleId"));

        return adminModel;
    }
}
