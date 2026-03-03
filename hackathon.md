# Java 21 Intern Hackathon: Personal Budget Tracker

This project is a starter template for a Java 21-based budget tracking application. It includes several "Challenges" designed to test knowledge of **Records**, **Streams**, **NIO.2**, and **Functional Programming**.

---

## 1. Project Source Code (`BudgetApp.java`)

```java
package com.replace_me.hackathon; // CHALLENGE 6: Change this to com.{yourname}.hackathon

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * HACKATHON: ADVANCED PERSONAL BUDGET TRACKER (JAVA 21 EDITION)
 * * CHALLENGES FOR INTERNS:
 * 1-4.   CORE LOGIC: Filtering, Aggregate Calculations, Budget Enforcement.
 * 5.     WIRING: Dependency Injection in Main, Service, and Controller.
 * 6.     PACKAGING: Modularize the project structure.
 * 7.     SORTING: Comparable (Date) and Comparator (Amount).
 * 8.     JAVA 21: Records and Unnamed Variables.
 * 9-11.  FUNCTIONAL API: Predicates, Functions, and Consumers.
 * 12-16. ADVANCED RETRIEVAL: Partitioning, Statistics, Joining, and Reductions.
 */

// ============================================================================
// 1. EXCEPTION LAYER (Intended: com.{name}.hackathon.exception)
// ============================================================================

class BudgetException extends RuntimeException {
    public BudgetException(String message) { super(message); }
}

class DataPersistenceException extends Exception {
    public DataPersistenceException(String message, Throwable cause) { super(message, cause); }
}

// ============================================================================
// 2. ENTITY LAYER - JAVA 21 RECORDS (Intended: com.{name}.hackathon.model)
// ============================================================================

enum TransactionType { INCOME, EXPENSE }

/**
 * CHALLENGE 7: Implement Comparable for natural sorting by Date (newest first).
 */
record Transaction(
    String id,
    LocalDate date,
    String description,
    double amount,
    String categoryName,
    TransactionType type
) {
    // TODO: CHALLENGE 7 - Add Comparable implementation here
   
    public String toCsv() {
        return String.join(",", id, date.toString(), description, String.valueOf(amount), categoryName, type.name());
    }
}

record Category(String name, double budgetLimit) {
    public String toCsv() { return name + "," + budgetLimit; }
}

record UserProfile(String username, double monthlySavingsGoal) {
    public String toCsv() { return username + "," + monthlySavingsGoal; }
}

// ============================================================================
// 3. REPOSITORY LAYER (Intended: com.{name}.hackathon.repository)
// ============================================================================

interface BaseRepository<T> {
    List<T> findAll();
    void save(T entity) throws DataPersistenceException;
}

class TransactionRepository implements BaseRepository<Transaction> {
    private final Path path = Paths.get("data_transactions.csv");

    public TransactionRepository() {
        try { if(!Files.exists(path)) Files.write(path, "id,date,desc,amt,cat,type\n".getBytes()); }
        catch(IOException _) {}
    }

    @Override
    public List<Transaction> findAll() {
        try (var lines = Files.lines(path)) {
            return lines.skip(1)
                .map(l -> l.split(","))
                .map(p -> new Transaction(p[0], LocalDate.parse(p[1]), p[2], Double.parseDouble(p[3]), p[4], TransactionType.valueOf(p[5])))
                .collect(Collectors.toList());
        } catch (IOException e) { return new ArrayList<>(); }
    }

    @Override
    public void save(Transaction t) throws DataPersistenceException {
        try { Files.write(path, (t.toCsv() + "\n").getBytes(), StandardOpenOption.APPEND); }
        catch (IOException e) { throw new DataPersistenceException("IO Failure", e); }
    }
}

class CategoryRepository implements BaseRepository<Category> {
    private final Path path = Paths.get("data_categories.csv");

    public CategoryRepository() {
        try { if(!Files.exists(path)) Files.write(path, "name,limit\n".getBytes()); }
        catch(IOException _) {}
    }

    @Override
    public List<Category> findAll() {
        try (var lines = Files.lines(path)) {
            return lines.skip(1).map(l -> l.split(",")).map(p -> new Category(p[0], Double.parseDouble(p[1]))).collect(Collectors.toList());
        } catch (IOException e) { return new ArrayList<>(); }
    }

    @Override
    public void save(Category c) throws DataPersistenceException {
        try { Files.write(path, (c.toCsv() + "\n").getBytes(), StandardOpenOption.APPEND); }
        catch (IOException e) { throw new DataPersistenceException("IO Failure", e); }
    }
}

class UserProfileRepository {
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
}

// ============================================================================
// 4. SERVICE LAYER (Intended: com.{name}.hackathon.service)
// ============================================================================

class BudgetService {
   
    // TODO: CHALLENGE 5 (Part D/E) - Define repos and implement constructor for wiring

    public Map<Boolean, List<Transaction>> getPartitionedTransactions() {
        // CHALLENGE 12: PARTITIONING DATA
        // TODO: Implement using .stream().collect(Collectors.partitioningBy(t -> t.type() == TransactionType.INCOME))
        return new HashMap<>();
    }

    public DoubleSummaryStatistics getExpenseStatistics() {
        // CHALLENGE 13: STATISTICAL SUMMARY
        // TODO: Implement using .stream().filter(expenses).mapToDouble(t -> t.amount()).()
        return new DoubleSummaryStatistics();
    }

    public boolean hasHighValueTransaction(String category, double threshold) {
        // CHALLENGE 14: EXISTENCE & THRESHOLDS
        // TODO: Implement using .stream().anyMatch(...)
        return false;
    }

    public Optional<Transaction> getHighestExpense() {
        // CHALLENGE 15: TOP EXPENSE FINDER
        // TODO: Implement using .stream().filter(expenses).max(Comparator.comparingDouble(...))
        return Optional.empty();
    }

    public String getCategoryReport() {
        // CHALLENGE 16: DATA JOINING
        // TODO: Implement using .stream().map(...).distinct().sorted().collect(Collectors.joining(", "))
        return "";
    }

    public List<Transaction> filterTransactions(Predicate<Transaction> filter) {
        // TODO: CHALLENGE 9 - Implementation needed
        return new ArrayList<>();
    }

    public Set<String> getUniqueDescriptions() {
        // TODO: CHALLENGE 10 - Implementation needed
        return new HashSet<>();
    }

    public void addTransaction(Transaction t) throws DataPersistenceException {
        if (t.type() == TransactionType.EXPENSE) {
            // TODO: CHALLENGE 3 - Implement budget ceiling check
        }
        // TODO: CHALLENGE 5 - Save via transRepo
    }

    public List<Transaction> getTransactionsSortedByAmount() {
        // TODO: CHALLENGE 7 - Implement sorting
        return new ArrayList<>();
    }

    public List<Transaction> fetchAllSortedByDate() {
        // TODO: CHALLENGE 7 - Implement sorting
        return new ArrayList<>();
    }

    public String getGoalStatus() {
        // TODO: CHALLENGE 2 - Implement calculation (Income - Expense) vs Goal
        return "Pending...";
    }

    public Map<String, Double>      getSpendingByCategory() {
        // TODO: CHALLENGE 4 - Implement groupingBy
        return new HashMap<>();
    }
}

// ============================================================================
// 5. CONTROLLER LAYER (Intended: com.{name}.hackathon.controller)
// ============================================================================

class AppController {
   
    // TODO: CHALLENGE 5 (Part A/B) - Define and wire Service & Category Repo

    public void handleAddTransaction(String desc, String amtStr, String cat, String typeStr) {
        try {
            // TODO: Parse inputs and call service.addTransaction
            System.out.println("Transaction recorded.");
        } catch (Exception e) {
            System.err.println("Controller Error: " + e.getMessage());
        }
    }

    public void addCategory(String name, String limitStr) {
        try {
            // TODO: CHALLENGE 5 (Part C) - Wire call to catRepo.save()
            System.out.println("Category added.");
        } catch (Exception e) {
            System.err.println("Category Error: " + e.getMessage());
        }
    }

    public void listTransactions(boolean sortByAmount) {
        // TODO: CHALLENGE 7 - Wire service calls for sorting
    }

    public void showAdvancedStats() {
        System.out.println("\n--- ADVANCED FINANCIAL INSIGHTS ---");
        // TODO: CHALLENGE 13 & 15 & 16 - Call service methods and display results
        System.out.println("------------------------------------");
    }

    public void showDashboard() {
        System.out.println("\n--- BUDGET DASHBOARD ---");
        // TODO: CHALLENGE 4 & 12 - Integrate summary and partitioning count
        System.out.println("-------------------------");
    }
}

// ============================================================================
// 6. MAIN APP / RUNNER
// ============================================================================

public class BudgetApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== JAVA 21 INTERN HACKATHON: PERSONAL BUDGET TRACKER ===");

        // CHALLENGE 5: ARCHITECTURAL WIRING
        // TODO: Wire layers together (Repos -> Service -> Controller)
       
        AppController controller = null; // CHALLENGE: Wire your controller here!

        if (controller == null) {
            System.err.println("\n[!] FATAL: Application wiring incomplete.");
            System.exit(1);
        }

        while (true) {
            System.out.println("\nMENU: [1] Add Trans | [2] List (Date) | [3] List (Amount) | [4] Stats | [5] Add Cat | [6] Summary | [7] Exit");
            System.out.print("Input: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Desc: "); String d = sc.nextLine();
                    System.out.print("Amt: "); String a = sc.nextLine();
                    System.out.print("Cat: "); String c = sc.nextLine();
                    System.out.print("Type: "); String t = sc.nextLine();
                    // TODO: Wire controller
                }
                case "2" -> { /* TODO: List by date */ }
                case "3" -> { /* TODO: List by amount */ }
                case "4" -> {
                    // TODO: Wire showAdvancedStats()
                }
                case "5" -> {
                    System.out.print("Name: "); String n = sc.nextLine();
                    System.out.print("Limit: "); String l = sc.nextLine();
                    // TODO: Wire controller
                }
                case "6" -> { /* TODO: Wire showDashboard() */ }
                case "7" -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
```


# Maven Configuration

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="[http://maven.apache.org/POM/4.0.0](http://maven.apache.org/POM/4.0.0)"
         xmlns:xsi="[http://www.w3.org/2001/XMLSchema-instance](http://www.w3.org/2001/XMLSchema-instance)"
         xsi:schemaLocation="[http://maven.apache.org/POM/4.0.0](http://maven.apache.org/POM/4.0.0) [http://maven.apache.org/xsd/maven-4.0.0.xsd](http://maven.apache.org/xsd/maven-4.0.0.xsd)">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.hackathon.budget</groupId>
    <artifactId>personal-budget-app</artifactId>
    <version>2.0-SNAPSHOT</version>
    <name>Java 21 Personal Budget Tracker</name>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.12.1</version>
                <configuration>
                    <release>21</release>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.1.1</version>
                <configuration>
                    <mainClass>com.replace_me.hackathon.BudgetApp</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

