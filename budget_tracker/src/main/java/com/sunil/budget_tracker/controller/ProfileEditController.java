
package com.sunil.budget_tracker.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sunil.budget_tracker.model.UserProfile;
import com.sunil.budget_tracker.model.Users;
import com.sunil.budget_tracker.service.UserProfileService;
import com.sunil.budget_tracker.service.UserService;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.io.IOException;


@Controller
@RequestMapping("/controller2")
public class ProfileEditController{

@Autowired

private UserProfileService userProfileService;


@Autowired

private UserService userService;


@GetMapping("/edit")
public String editName(){

return "editProfile";

}

@PostMapping("/editProfile")
public String editProfile(@AuthenticationPrincipal UserDetails userDetails,@RequestParam("name") String newName,
@RequestParam("image")MultipartFile  newImage,@RequestParam(value="saveGoal", required=false) Double newGoal){

String fileName;


String sname = userDetails.getUsername();

Optional<UserProfile> profileOpt = userProfileService.findByUserName(sname);

UserProfile profile = profileOpt.get();



String oldName = profile.getUsername();
String oldpicture = profile.getProfilePicture();
double oldMonthlyGoal = profile.getMonthlySavingsGoal();

//_____________________________





try {


if(newName == null || newName.trim().isEmpty()){

profile.setUsername(oldName);



}else{

    profile.setUsername(newName);

}if(newImage == null|| newImage.isEmpty()){

profile.setProfilePicture(oldpicture);

}else{

fileName = newImage.getOriginalFilename();

profile.setProfilePicture("/upload/" +fileName);
   
    Path path = Paths.get("upload/" + fileName); 
    
    
    Files.write(path, newImage.getBytes()); 

}
if(newGoal==null){

profile.setMonthlySavingsGoal(oldMonthlyGoal);

}else{


profile.setMonthlySavingsGoal(newGoal);

}

    
} catch (IOException e){ 

    e.printStackTrace(); 


}


userProfileService.saveUserProfile(profile);


return "redirect:/controller/myProfile";


}


@GetMapping("/password")

public String password(){

return "passwordSetter";

}

@PostMapping("/passwordSetter")
public String passwordSetter(@RequestParam("username")String username,@RequestParam("password") String password){


Optional<UserProfile> userProfiles = userProfileService.findByUserNamePass(username);

     System.out.println("My Profile Found " + userProfiles);

    if(userProfiles.isPresent()){

     UserProfile profile = userProfiles.get();



      Users user = new Users();

      user.setPassword(password);
      user.setUserName(username);
      profile.setUser(user);


       userService.save(user);


        userProfileService.save(profile);  
    }

  return "redirect:/controller/budgetApp";

}


@GetMapping("/role")

public String role(){

    return "setRole";


}


@PostMapping("/setRole")
public String setRole(){

  return "redirect:/controller/UserProfileCreation";
}

}