package com.example.bankingsystem.data.admin;

import com.example.bankingsystem.domain.model.Admin;
import com.example.bankingsystem.domain.model.Client;

import java.util.List;

public interface AdminStorage {
    List<Admin> getAllAdmin(String pattern);
    List<Admin> findById(int adminId);
}
