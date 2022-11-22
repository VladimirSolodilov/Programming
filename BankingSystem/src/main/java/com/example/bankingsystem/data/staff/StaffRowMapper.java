package com.example.bankingsystem.data.staff;

import com.example.bankingsystem.domain.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staffModel = new Staff();

        staffModel.setStaffId(rs.getInt("staff_id"));
        staffModel.setName(rs.getString("staff_name"));
        staffModel.setRoleId(rs.getInt("staff_id"));

        return staffModel;
    }
}
