package com.example.bankingsystem.domain.admin;

import com.example.bankingsystem.domain.model.Admin;
import com.example.bankingsystem.domain.model.JuridicalPerson;

import java.util.List;

public interface AdminService {

    List<Admin> getAdminList(String personName);
    List<Admin> getAdminByNameLike(String name);

}
