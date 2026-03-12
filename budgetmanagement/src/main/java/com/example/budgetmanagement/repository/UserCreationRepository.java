package com.example.budgetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.budgetmanagement.model.User;

public interface  UserCreationRepository extends JpaRepository<User,Long>{
        Optional<User> findByUserName(String userName);

}
