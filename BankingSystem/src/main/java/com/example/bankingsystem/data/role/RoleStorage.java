package com.example.bankingsystem.data.role;

import com.example.bankingsystem.domain.model.Role;

import java.util.Collection;
import java.util.List;

public interface RoleStorage {
    List<Role> getAllRoles();
    Collection<Role> getRoleById(int roleId);
    Role getRoleById1(int roleId);
}
