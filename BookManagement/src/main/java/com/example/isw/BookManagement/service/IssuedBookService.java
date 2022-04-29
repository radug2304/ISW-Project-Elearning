package com.example.isw.BookManagement.service;

import com.example.isw.BookManagement.common.Constants;
import com.example.isw.BookManagement.model.Book;
import com.example.isw.BookManagement.model.IssuedBook;
import com.example.isw.BookManagement.repository.IssuedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuedBookService {
    @Autowired
    private IssuedBookRepository issuedBookRepository;

    public List<IssuedBook> getAllIssuedBooks() {
        return issuedBookRepository.findAll();
    }

    public IssuedBook getIssuedBook(Integer id) {
        return issuedBookRepository.findById(id).get();
    }

    public Long getCountByBook(Book book) {
        return issuedBookRepository.countByBookAndReturned(book, Constants.BOOK_NOT_RETURNED);
    }

    public IssuedBook saveIssuedBook(IssuedBook issuedBook) {
        return issuedBookRepository.save(issuedBook);
    }

    public IssuedBook addNewIssuedBook(IssuedBook issuedBook) {
        issuedBook.setReturned( Constants.BOOK_NOT_RETURNED );
        return issuedBookRepository.save(issuedBook);
    }
}
