package com.example.isw.BookManagement.repository;

import com.example.isw.BookManagement.model.Book;
import com.example.isw.BookManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    public Book findByTag(String tag);
    public Book findByTitle(String title);
    public List<Book> findByCategory(Category category);
    public List<Book> findByCategoryAndStatus(Category category, Integer status);
    public Long countByStatus(Integer status);
}
