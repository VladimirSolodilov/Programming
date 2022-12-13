package com.example.bankingsystem.data.branch;

import com.example.bankingsystem.domain.model.Branch;

import java.util.List;

public interface BranchStorage {
    List<Branch> getBranchList();
    List<Branch> getBranchIdByName(String branchName);
    int branchRegistration(String name, String address);
}
