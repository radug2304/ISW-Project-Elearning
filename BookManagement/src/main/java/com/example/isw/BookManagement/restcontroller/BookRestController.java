package com.example.isw.BookManagement.restcontroller;

import com.example.isw.BookManagement.model.Book;
import com.example.isw.BookManagement.model.Category;
import com.example.isw.BookManagement.service.BookService;
import com.example.isw.BookManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/book")
public class BookRestController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value = {"/", "/list"})
	public List<Book> all() {
		return bookService.getAllBooks();
	}
	
	@GetMapping(value = "/{id}/list")
	public List<Book> get(@PathVariable(name = "id") Integer id) {
		Category category = categoryService.getCategoryById(id);
		return bookService.getBookByCategory( category );
	}
	
	@GetMapping(value = "/{id}/available")
	public List<Book> getAvailableBooks(@PathVariable(name = "id") Integer id) {
		Category category = categoryService.getCategoryById(id);
		return bookService.getAvailableByCategory( category );
	}
	
}
