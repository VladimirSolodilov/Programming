package com.example.bankingsystem.domain.admin;


import com.example.bankingsystem.data.admin.AdminStorage;
import com.example.bankingsystem.domain.model.Admin;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceDomain implements AdminService {

    @Autowired
    private AdminStorage adminStorage;

    @Override
    public List<Admin> getAdminList(String personName) {
        return adminStorage.getAllAdmin(personName);
    }

    @Override
    public List<Admin> getAdminByNameLike(String name) {
        return adminStorage.getAllAdmin(name);
    }
}
