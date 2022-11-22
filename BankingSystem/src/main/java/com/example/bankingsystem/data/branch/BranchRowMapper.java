package com.example.bankingsystem.data.branch;

import com.example.bankingsystem.domain.model.Branch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRowMapper implements RowMapper<Branch> {

    @Override
    public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
        Branch branchModel = new Branch();

        branchModel.setBranchId(rs.getInt("branch_id"));
        branchModel.setName(rs.getString("branch_name"));
        branchModel.setAddress(rs.getString("branch_address"));
        branchModel.setBankIdentificationCode(rs.getString("branch_bank" +
                "identificationCode"));

        return branchModel;
    }
}
