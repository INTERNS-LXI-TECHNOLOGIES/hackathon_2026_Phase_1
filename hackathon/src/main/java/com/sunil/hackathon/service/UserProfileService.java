package com.sunil.hackathon.service;

import com.sunil.hackathon.model.UserProfile;
import com.sunil.hackathon.repository.UserProfileRepository;
public class UserProfileService{

private UserProfileRepository userProfileRepository;

public UserProfileService(UserProfileRepository userProfileRepository){

    this.userProfileRepository = userProfileRepository;

}


public UserProfile addProfile(String name ,String monthlySavingsGoal){

 double converted = Double.parseDouble(monthlySavingsGoal);

 UserProfile up = new UserProfile(name,converted);


return userProfileRepository.save(up);



}

}