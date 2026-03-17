package com.sunil.budget_tracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sunil.budget_tracker.model.UserProfile;

import com.sunil.budget_tracker.model.Transaction;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private BudgetService budgetService;


    @Autowired
    UserProfileService userProfileService;
    


public String  createEmail(String name){


Optional<UserProfile> option = userProfileService.findByUserName(name);

UserProfile profile = option.get();

List<Transaction> transaction =  profile.getTransaction();

String status =  budgetService.getGoalStatus(profile, transaction);


try{
System.out.println("sunil sunil sunil cheking start 1 " +status);

if(status.equals("Goal Achieved")){

System.out.println("sunil sunil sunil cheking");

String toEmail = "sunusunilsunil@gmail.com"; // recipient email address
String subject = "Budget Alert";     // the subject/title of the email
String body = "Hello " + name + " You have achieved your monthly savings goal! "; // the actual content/message




sendSimpleEmail(toEmail,subject,body);



System.out.println("sunil sunil sunil end ");


}

}catch(Exception e){

     e.printStackTrace();
    return "Failed to send email";

}





return "controller/budgetApp";

}


  





    public void sendSimpleEmail(String toEmail, String subject, String body) {

    
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sunusunilsunil@gmail.com"); // same as spring.mail.username
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        System.out.println("Mail sent to: " + toEmail);

     



    } 
}