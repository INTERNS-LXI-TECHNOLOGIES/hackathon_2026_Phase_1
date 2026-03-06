package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
@RestController
@RequestMapping("/api")
public class Controller { 


   // List <BigDecimal> balance = new ArrayList<> ();
    
     
    

   private BigDecimal balance = BigDecimal.ZERO;

    // Deposit
    @PostMapping("/deposit")
    public String deposit(@RequestBody BigDecimal amount) {

        balance = balance.add(amount);

        return "Deposited: " + amount + " | Current Balance: " + balance;
    }

    @PostMapping ("/withdrawamount")
    public String withDraw (@requestparam BigDecimal amount) {

        if (balance!=null){

                balance.compareTo(amount); 

        balance = balance.subtract( amount);}

        return "withdraw amount" + amount + " | Current Balance" + balance;

    }

         @GetMapping("/get")
             public String getBalancce(  ){

        
                   return "Account balance" + balance;
    }
}