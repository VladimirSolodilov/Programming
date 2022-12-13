package com.example.bankingsystem.data.branch;

import com.example.bankingsystem.domain.model.Branch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRowMapper implements RowMapper<Branch> {
    @Override
    public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
        Branch branch = new Branch();

        branch.setBranchId(rs.getInt("BranchId"));
        branch.setBankId(rs.getInt("BankId"));
        branch.setBranchName(rs.getString("BranchName"));
        branch.setAddress(rs.getString("Address"));

        return branch;
    }
}
