package com.example.bankingsystem.data.role;

import com.example.bankingsystem.domain.model.Role;

import java.util.Collection;
import java.util.List;

public interface RoleStorage {
    Collection<Role> getRoleByIdCollection(int roleId);
}
