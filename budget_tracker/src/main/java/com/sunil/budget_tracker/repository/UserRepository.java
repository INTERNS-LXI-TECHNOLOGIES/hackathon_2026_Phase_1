
package com.sunil.budget_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunil.budget_tracker.model.Users;

public interface UserRepository extends JpaRepository<Users ,Integer>{

 public  Users findByUserName(String username);

}


