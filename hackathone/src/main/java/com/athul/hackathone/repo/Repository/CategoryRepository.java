package com.athul.hackathone.repo.Repository;
import com.athul.hackathone.execption.DataPersistenceException;
import com.athul.hackathone.repo.BaseRepository;
import com.athul.hackathone.model.Category;

import java.io.IOException;
import  java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import com.athul.hackathone.model.Cartogory;
public class CategoryRepository implements BaseRepository<Category> {
    private final Path path = Paths.get("data_categories.csv");

    public CategoryRepository() {
        try { if(!Files.exists(path)) Files.write(path, "name,limit\n".getBytes()); }
        catch(IOException u) {}
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