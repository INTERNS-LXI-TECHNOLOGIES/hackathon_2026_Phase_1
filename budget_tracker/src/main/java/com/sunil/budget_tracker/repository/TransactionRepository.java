package com.sunil.budget_tracker.repository;
import com.sunil.budget_tracker.repository.BaseRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.sunil.budget_tracker.model.Transaction;
@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long>{



   
/* 
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

       */

    }

