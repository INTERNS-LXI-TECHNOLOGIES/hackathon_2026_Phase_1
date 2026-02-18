package com.akshay.repository;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import com.akshay.hackathon.entity.Category;
import com.akshay.hackathon.exception.DataPersistenceException;

import java.util.ArrayList;

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
    public void save(Category c) {
        try { Files.write(path, (c.toCsv() + "\n").getBytes(), StandardOpenOption.APPEND); }
        catch (IOException e) { throw new DataPersistenceException("IO Failure", e); }
    }
}
