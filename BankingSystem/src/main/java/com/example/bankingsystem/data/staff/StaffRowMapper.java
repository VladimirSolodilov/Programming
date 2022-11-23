package com.example.bankingsystem.data.staff;

import com.example.bankingsystem.domain.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staffModel = new Staff();

        staffModel.setStaffId(rs.getInt("StaffId"));
        staffModel.setName(rs.getString("Name"));
        staffModel.setRoleId(rs.getInt("RoleId"));

        return staffModel;
    }
}
