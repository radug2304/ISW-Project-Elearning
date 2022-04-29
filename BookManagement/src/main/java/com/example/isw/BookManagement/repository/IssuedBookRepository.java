package com.example.isw.BookManagement.repository;

import com.example.isw.BookManagement.model.Book;
import com.example.isw.BookManagement.model.IssuedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedBookRepository extends JpaRepository<IssuedBook, Integer> {
    public Long countByBookAndReturned(Book book, Integer returned);
}
