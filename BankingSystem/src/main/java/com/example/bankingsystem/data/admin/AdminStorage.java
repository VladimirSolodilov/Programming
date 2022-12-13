package com.example.bankingsystem.data.admin;

import com.example.bankingsystem.domain.model.Admin;

import java.util.List;

public interface AdminStorage {
    List<Admin> getAllAdmin(String pattern);
}
