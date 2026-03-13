package com.sunil.budget_tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.budget_tracker.model.UserProfile;
import com.sunil.budget_tracker.repository.UserProfileRepository;

import java.util.List;
import java.util.Optional;;


@Service
public class UserProfileService{

@Autowired
private UserProfileRepository userProfileRepository;

public void saveUserProfile(UserProfile user){

userProfileRepository.save(user);


}    


public Optional<UserProfile> findProfile(long user){

Optional<UserProfile> user1 = userProfileRepository.findById(user);


return user1;


}


public List<UserProfile> findAll(){

 return userProfileRepository.findAll();

  
}


}