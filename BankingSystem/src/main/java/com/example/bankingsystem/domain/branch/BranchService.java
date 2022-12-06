package com.example.bankingsystem.domain.branch;

import com.example.bankingsystem.domain.model.Branch;

import java.util.List;

public interface BranchService {

    List<Branch> getBranchList();
    List<Branch> getBranchIdByName(String branchName);

}
