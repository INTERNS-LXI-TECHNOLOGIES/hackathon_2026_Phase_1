package com.sunil.hackathon.repository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sunil.hackathon.exception.DataPersistenceException;
import com.sunil.hackathon.model.Category;

public class CategoryRepository implements BaseRepository<Category> {
    
    private final Path path = Paths.get("data_categories.csv");

    public CategoryRepository() {

        try { if(!Files.exists(path)) Files.write(path, "name,limit\n".getBytes()); }
        catch(IOException e) {}

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