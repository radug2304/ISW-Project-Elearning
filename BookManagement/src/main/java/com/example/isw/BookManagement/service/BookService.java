package com.example.isw.BookManagement.service;

import com.example.isw.BookManagement.common.Constants;
import com.example.isw.BookManagement.model.Book;
import com.example.isw.BookManagement.model.Category;
import com.example.isw.BookManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssuedBookService issuedBookService;

    public Long getCountTotal() {
        return bookRepository.count();
    }

    public Long getIssuedBooksTotal() {
        return bookRepository.countByStatus(Constants.BOOK_ISSUED);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).get();
    }

    public Book getBookByTag(String tag) {
        return bookRepository.findByTag(tag);
    }

    public List<Book> getAllBooksById(List<Integer> ids) {
        return bookRepository.findAllById(ids);
    }

    public List<Book> getBookByCategory(Category category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> getAvailableByCategory(Category category) {
        return bookRepository.findByCategoryAndStatus(category, Constants.BOOK_AVAILABLE);
    }

    public Book addNewBook(Book book) {
        book.setCreateDate(new Date());
        book.setStatus( Constants.BOOK_AVAILABLE );
        return bookRepository.save(book);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public boolean hasUsage(Book book) {
        return issuedBookService.getCountByBook(book) > 0;
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }


}
