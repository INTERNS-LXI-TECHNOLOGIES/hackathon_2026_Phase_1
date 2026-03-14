
package com.sunil.budget_tracker.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sunil.budget_tracker.model.UserProfile;
import com.sunil.budget_tracker.service.UserProfileService;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

import java.io.IOException;


@Controller
@RequestMapping("/controller2")
public class ProfileEditController{

@Autowired

private UserProfileService userProfileService;



@GetMapping("/edit")
public String editName(){

return "editProfile";

}

@PostMapping("/editProfile")
public String editProfile(@AuthenticationPrincipal UserDetails userDetails,@RequestParam("name") String newName,
@RequestParam("image")MultipartFile  newImage,@RequestParam("saveGoal")double newGoal){

String sname = userDetails.getUsername();

Optional<UserProfile> profileOpt = userProfileService.findByUserName(sname);

UserProfile profile = profileOpt.get();

profile.setUsername(newName);

String fileName = newImage.getOriginalFilename();
profile.setProfilePicture("/upload/" +fileName);

try {
   
    Path path = Paths.get("upload/" + fileName); 
    
    // 2. Files performs the physical save
    Files.write(path, newImage.getBytes()); 
    
} catch (IOException e) { 
    
    e.printStackTrace(); 
}



profile.setMonthlySavingsGoal(newGoal);

userProfileService.saveUserProfile(profile);


return "redirect:/controller/myProfile";


}


}