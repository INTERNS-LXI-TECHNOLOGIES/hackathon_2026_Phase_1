package com.sunil.budget_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.budget_tracker.model.UserProfile;
import com.sunil.budget_tracker.repository.UserProfileRepository;


@Service
public class UserProfileService{

@Autowired
private UserProfileRepository userProfileRepository;

public String saveUserProfile(UserProfile user){

userProfileRepository.save(user);

return null; 

}    


}