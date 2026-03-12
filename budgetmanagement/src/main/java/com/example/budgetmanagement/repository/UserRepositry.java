package com.example.budgetmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.budgetmanagement.model.*;
import java.util.Optional;
@Repository
public interface UserRepositry  extends JpaRepository<User,Long>{
    Optional<User> findByUserName(String userName);
}
