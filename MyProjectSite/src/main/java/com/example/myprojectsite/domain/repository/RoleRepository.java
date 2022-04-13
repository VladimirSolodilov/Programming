package com.example.myprojectsite.domain.repository;

import com.example.myprojectsite.domain.model.user.Role;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(value = "SELECT * FROM role WHERE role_name = :role_name limit 1;", nativeQuery = true)
    Role findRoleByName(@Param("role_name") @NonNull String role_name);
}