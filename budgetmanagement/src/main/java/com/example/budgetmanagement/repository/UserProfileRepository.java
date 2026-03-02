package com.example.budgetmanagement.repository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.budgetmanagement.model.*;
public class UserProfileRepository {
    private final Path path = Paths.get("data_user.csv");
    public UserProfile load() {
        try (var lines = Files.lines(path)) {
            String line = lines.findFirst().orElse("Guest,0");
            String[] p = line.split(",");
            return new UserProfile(p[0], Double.parseDouble(p[1]));
        } catch (IOException e) { return new UserProfile("Candidate", 500.0); }
    }
    public void save(UserProfile u) {
        try { Files.write(path, u.toCsv().getBytes()); } catch (IOException e) {}
    }
}