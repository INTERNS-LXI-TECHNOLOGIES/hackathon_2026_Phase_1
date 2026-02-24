package com.sunil.hackathon.repository;
import com.sunil.hackathon.exception.*;
import com.sunil.hackathon.model.Transaction;
import com.sunil.hackathon.model.TransactionType;
import com.sunil.hackathon.model.UserProfile;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class UserProfileRepository {


    private final Path path = Paths.get("data_user.csv");
    public UserProfile load() {
        try (var lines = Files.lines(path)) {
            String line = lines.findFirst().orElse("Guest,0");
            String[] p = line.split(",");
            return new UserProfile(p[0], Double.parseDouble(p[1]));
        } catch (IOException e) { return new UserProfile("Candidate", 500.0); }
        
    }

 

    public UserProfile save(UserProfile u) {

        try { Files.write(path, u.toCsv().getBytes()); } catch (IOException a) {}
        return u;
    }



}