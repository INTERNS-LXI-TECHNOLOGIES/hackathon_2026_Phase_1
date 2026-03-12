package com.example.budgetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.budgetmanagement.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

}