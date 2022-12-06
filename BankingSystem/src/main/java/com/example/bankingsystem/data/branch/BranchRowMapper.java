package com.example.bankingsystem.data.branch;

import com.example.bankingsystem.domain.model.Branch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRowMapper implements RowMapper<Branch> {

    @Override
    public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
        Branch branchModel = new Branch();

        branchModel.setBranchId(rs.getInt("BranchId"));
        branchModel.setBankId(rs.getInt("BankId"));
        branchModel.setBranchName(rs.getString("Name"));
        branchModel.setAddress(rs.getString("Address"));
        branchModel.setBankIdentificationCode(rs.getString("Bank" +
                "IdentificationCode"));

        return branchModel;
    }
}
