package com.example.bankingsystem.data.branch;

import com.example.bankingsystem.domain.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BranchStorageDB implements BranchStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    String getBranchList = "Select * from Branch";
    String getBranchIdByName = "Select * from Branch where Branch.BranchName Like ?";
    String branchRegistration = "Insert Branch Values(?, ?, ?)";

    @Override
    public List<Branch> getBranchList() {
        return jdbcTemplate.query(getBranchList, new BranchRowMapper());
    }

    @Override
    public List<Branch> getBranchIdByName(String branchName) {
        return jdbcTemplate.query(getBranchIdByName, new BranchRowMapper(), branchName);
    }
    @Override
    public int branchRegistration(String name, String address) {
        return jdbcTemplate.update(branchRegistration, 1, name, address);
    }
}
