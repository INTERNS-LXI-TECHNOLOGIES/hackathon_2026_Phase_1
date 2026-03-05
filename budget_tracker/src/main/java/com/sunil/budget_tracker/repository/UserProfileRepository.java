
package com.sunil.budget_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunil.budget_tracker.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {





   /*

    private final Path path = Paths.get("data_user.csv");
    public UserProfile load() {
        try (var lines = Files.lines(path)) {
            String line = lines.findFirst().orElse("Guest,0");
            String[] p = line.split(",");
            return new UserProfile(p[0], Double.parseDouble(p[1]));
        } catch (IOException e) { return new UserProfile("Candidate", 500.0); }
    }
    public void save(UserProfile u) {
        try { Files.write(path, u.toCsv().getBytes()); } catch (IOException _) {}
    }

   */
   



}