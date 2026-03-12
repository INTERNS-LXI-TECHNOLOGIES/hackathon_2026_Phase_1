package com.example.budgetmanagement.service;
import com.example.budgetmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.budgetmanagement.model.*;
public class UserCreationService {
      @Autowired
    private UserCreationRepository userCreationRepository;

    public void saveUser(UserCreation user){
        userCreationRepository.save(user);
    }
}
