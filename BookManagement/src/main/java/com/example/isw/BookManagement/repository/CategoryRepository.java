package com.example.isw.BookManagement.repository;

import com.example.isw.BookManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public List<Category> findAllByOrderByNameAsc();
    public Category findByName(String name);
    public Category findByShortName(String shortName);
}
