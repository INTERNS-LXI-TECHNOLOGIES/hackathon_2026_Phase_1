package com.sunil.hackathon.repository;
import com.sunil.hackathon.model.Transaction;
import com.sunil.hackathon.exception.DataPersistenceException;


import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import com.sunil.hackathon.model.TransactionType;


public  class TransactionRepository implements BaseRepository<Transaction> {
    private final Path path = Paths.get("data_transactions.csv");

    public TransactionRepository() {
        try { if(!Files.exists(path)) Files.write(path, "id,date,desc,amt,cat,type\n".getBytes()); }
        catch(IOException e) {}
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
