package com.example.bankingsystem.domain.branch;

import com.example.bankingsystem.data.branch.BranchStorage;
import com.example.bankingsystem.domain.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceDomain implements BranchService {
    @Autowired
    private BranchStorage branchStorage;

    @Override
    public List<Branch> getBranchList() {
        return branchStorage.getBranchList();
    }

    @Override
    public List<Branch> getBranchIdByName(String branchName) {
        return branchStorage.getBranchIdByName(branchName);
    }

    @Override
    public int branchRegistration(String name, String address) {
        return branchStorage.branchRegistration(name, address);
    }
}
