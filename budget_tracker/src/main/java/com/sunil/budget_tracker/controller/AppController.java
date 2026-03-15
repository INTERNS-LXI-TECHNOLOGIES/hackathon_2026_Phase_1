package com.sunil.budget_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sunil.budget_tracker.exception.DataPersistenceException;
import com.sunil.budget_tracker.model.Transaction;
import com.sunil.budget_tracker.model.UserProfile;
import com.sunil.budget_tracker.repository.TransactionRepository;
import com.sunil.budget_tracker.service.BudgetService;
import com.sunil.budget_tracker.service.CategoryService;
import com.sunil.budget_tracker.service.UserProfileService;
import com.sunil.budget_tracker.service.UserService;



import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

import com.sunil.budget_tracker.model.Category;


//pagenation imports 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
//logger implimenting 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//image imports 

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Controller
@RequestMapping("/controller")
       //@RequiredArgsConsucturvṭño0r
public class AppController {

private static final Logger logger = LoggerFactory.getLogger(AppController.class);


@Autowired
private  BudgetService budgetService;   

@Autowired
private CategoryService categoryService;

@Autowired
private TransactionRepository transactionRepository;

@Autowired
private UserProfileService userProfileService;


@Autowired
private UserService userService;



@GetMapping("/budgetApp")

    public String home(@AuthenticationPrincipal UserDetails userDetails,Model model){


                              String name = userDetails.getUsername();   

    Optional<UserProfile> optional = userProfileService.findByUserName(name);

    UserProfile  profile =  optional.get();
     
    List<Transaction> transaction = profile.getTransaction();
 

        
               //# 1
              String status  =    budgetService.getGoalStatus(profile,transaction);
              model.addAttribute("goalMessage",status);

              //# 2




                  Optional<Transaction> transactions =  budgetService.getHighestExpense(transaction);
                  model.addAttribute("highValueTransaction", transactions.get().getAmount());  

               
                 String name1 =  userDetails.getUsername();

                Optional<UserProfile> optionalUser = userProfileService.findByUserName(name1);


                if(optionalUser.isPresent()){

              UserProfile userProfile1 = optionalUser.get();

               List<Transaction> transactions3 = userProfile1.getTransaction();



               model.addAttribute("transactionPage", transactions3);

                }
  
                // # 3 old code 
               
            /*         Page<Transaction> transactionPage =  transactionRepository.findAll(PageRequest.of(page,7));
              model.addAttribute("transactionPage", transactionPage);   */
       
    
               
  

        return "budgetApp"; // Matches src/main/resources/templates/budgetApp.html

    }

    @GetMapping("/findAddTransaction")
    public String showForm(Model model) {
        model.addAttribute("transaction" ,new Transaction());

        return "AddTransaction";

    }


    // TODO: CHALLENGE 5 (Part A/B) - Define and wire Service & Category Repo

    // Object Binding (Best for forms) Transaction transaction  

    @PostMapping("/addTransaction")
    public String handleAddTransaction(@AuthenticationPrincipal UserDetails userDetails,Model model, Transaction transaction,@RequestParam int categoryId) throws DataPersistenceException {



        try {
              
          Optional<Category> category =  categoryService.findById(categoryId);

          String name = userDetails.getUsername();

          Optional<UserProfile> userProfile = userProfileService.findByUserName(name);

     if(category.isPresent()&&userProfile.isPresent()){

    transaction.setCategory(category.get());
    transaction.setUserProfileId(userProfile.get());

    }
                  
             
       budgetService.addTransaction(transaction);
            
             
            // TODO: Parse inputs and call service.addTransaction
            logger.info("Transaction Recorded");
          
        } catch (Exception e) {
           
            logger.error("Controller Error");

        }

    return "redirect:/controller/budgetApp";
        
    }

    @GetMapping("/findAddCategory")
    public String addCategory(Model model) {

      model.addAttribute("category",new Category());  
    
      return "AddCategory";

    }


    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category){
    
        try {

            categoryService.addCategory(category); 


            // TODO: CHALLENGE 5 (Part C) - Wire call to catRepo.save()
            System.out.println("Category added.");
        } catch (Exception e) {
            System.err.println("Category Error: " + e.getMessage());
        }

        return "redirect:/controller/budgetApp";
    }






    @GetMapping("/sortByAmount")
    public String  listTransactions(@AuthenticationPrincipal UserDetails userDetails,Model model) {


     String name = userDetails.getUsername();   

    Optional<UserProfile> optional = userProfileService.findByUserName(name);

    UserProfile  profile =  optional.get();
     
    List<Transaction> transaction = profile.getTransaction();
    
    List<Transaction> sortTransactions =    budgetService. getTransactionsSortedByAmount(transaction);

       


      model.addAttribute("transactions",sortTransactions);

      return "SortByAmountTransaction";

        // TODO: CHALLENGE 7 - Wire service calls for sorting


    }


@GetMapping("/sortByDate")
public String sortByDate(@AuthenticationPrincipal UserDetails userDetails,Model model){

    String name = userDetails.getUsername();

 Optional<UserProfile> optional = userProfileService.findByUserName(name);

 UserProfile  userProfile = optional.get();

 List<Transaction> transactions = userProfile.getTransaction();


 List<Transaction>  sortByDate = budgetService.fetchAllSortedByDate(transactions);

  model.addAttribute("transactionByDate",sortByDate);  

   return "SortByDate"; 
}





 
    @GetMapping("/showAdvancedStats")
    public String  showAdvancedStats(@AuthenticationPrincipal UserDetails userDetails,Model model){

    
      String name =   userDetails.getUsername();

     Optional<UserProfile> optional =  userProfileService.findByUserName(name);
   
     UserProfile userProfile = optional.get();
      
         List<Transaction> transactions = userProfile.getTransaction();

     //13
     DoubleSummaryStatistics statistics =  budgetService.getExpenseStatistics(transactions);
     model.addAttribute("doubleSummaryCount",statistics.getCount());
     model.addAttribute("doubleSummarySum",statistics.getSum());
     model.addAttribute("doubleSummaryAverage",statistics.getAverage());
     model.addAttribute("doubleSummaryMax",statistics.getMax());
     model.addAttribute("doubleSummaryMin",statistics.getMin());
     model.addAttribute("doubleSummaryClass",statistics.getClass());


     //15



     Optional<Transaction> transaction =  budgetService.getHighestExpense(transactions);
      
     model.addAttribute("highValueTransaction", transaction.get().getAmount());
      
     //16 
     List<String> category = budgetService.getCategoryReport();

     model.addAttribute("category",category);


     return "SummaryCount";
    
       /*  System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
        // TODO: CHALLENGE 13 & 15 & 16 - Call service methods and display results
        System.out.println("------------------------------------");  */
        
    }
  

 // now working 

    @GetMapping("/showDashboard")
    public String showDashboard(@AuthenticationPrincipal UserDetails userDetails,Model model){

       
        // TODO: CHALLENGE 4 & 12 - Integrate summary and partitioning count
      

                              String name = userDetails.getUsername();   

    Optional<UserProfile> optional = userProfileService.findByUserName(name);

    UserProfile  profile =  optional.get();
     
    List<Transaction> transaction = profile.getTransaction();


         Map<Boolean,List<Transaction>>  transaction1 =   budgetService.getPartitionedTransactions(transaction);
        
         model.addAttribute("partitionedData",transaction1);

       
     Map<String,Double> transactions2 =  budgetService.getSpendingByCategory(transaction);
     
     model.addAttribute("spendingByCategory" , transactions2);
        
     
         return "ShowDashBord";

    }
 


    @GetMapping("/createUserProfile")
    public String createUserProfile(Model model){

     UserProfile userProfile = new UserProfile();
 
     model.addAttribute("userProfile", userProfile);

     return "UserProfileCreation"; 
     
    }

    @PostMapping("/saveUserProfile")
    public  String createUserForm(@ModelAttribute("userProfile")UserProfile userProfile,
     @RequestParam("imageFile")MultipartFile file, Model model) throws IOException{


        if(!file.isEmpty()){
  
         String uploadDir = "upload/";
         String fileName = file.getOriginalFilename();


         Path uploadPath = Paths.get(uploadDir);
  

            if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
       
            Path path = Paths.get("upload/" + fileName);
            Files.write(path,file.getBytes());

     userProfile.setProfilePicture("/upload/"+fileName);

    }

    
    userProfileService.saveUserProfile(userProfile);
  
    long id = userProfile.getId();



        return "redirect:/controller/userProfile/" + id;

        

    }


    @GetMapping("/userProfile/{id}")
public String userProfile(@PathVariable long id,Model model) {

     Optional<UserProfile> profile = userProfileService.findProfile(id);

     model.addAttribute("createdUserProfile",profile.get());

    return "UserProfileView";
}


@GetMapping("/viewAllUserProfile")
public String viewAllUserProfile(Model model){


List<UserProfile> uProfiles = userProfileService.findAll();

model.addAttribute("allUserProfile",uProfiles);

return "allUserProfile";

}


@GetMapping("/displayAchivedGoalStatus")
public String  displayAchivedGoalStatus(@AuthenticationPrincipal UserDetails userDetails,Model model){

                      String name = userDetails.getUsername();   

    Optional<UserProfile> optional = userProfileService.findByUserName(name);

    UserProfile  profile =  optional.get();
     
    List<Transaction> transaction = profile.getTransaction();



String status  =    budgetService.getGoalStatus(profile,transaction);

model.addAttribute("goalMessage",status);

return "GoalStatus";

}



@GetMapping("/findHasHighValueTransaction")
public String findHasHighValueTransaction(@AuthenticationPrincipal UserDetails userDetails,@RequestParam("category") String categoryName,@RequestParam("threshHold") double threshold,Model model){

                      String name = userDetails.getUsername();   

    Optional<UserProfile> optional = userProfileService.findByUserName(name);

    UserProfile  profile =  optional.get();
     
    List<Transaction> transaction = profile.getTransaction();


 String result =  budgetService.hasHighValueTransaction(transaction,categoryName, threshold);    

 model.addAttribute("status",result);

 return "ThresholdStatus";


}



@GetMapping("/languageSwitcher") 
public String languageSwitcher(){




    return "LanguageSwitcher";


}


@GetMapping("/accessDenied")
public String accessDenied() {
    return "accessDenied";
}


@GetMapping("admin/adminPage")
public String adminPage() {
    return "adminPage";
}


@GetMapping("/myProfile")
public String myProfile(@AuthenticationPrincipal UserDetails userDetails,Model model){
    
String userName = userDetails.getUsername(); 



Optional<UserProfile> userProfile = userProfileService.findByUserName(userName);



model.addAttribute("userProfile",userProfile.get());

return "myProfile";

}




}
