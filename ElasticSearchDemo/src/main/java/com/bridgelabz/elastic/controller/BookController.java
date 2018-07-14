package com.bridgelabz.elastic.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.elastic.dao.BookDao;
import com.bridgelabz.elastic.model.Book;

@RestController
@RequestMapping(value = "/bookstore")
public class BookController {

	private BookDao bookDao;

	public BookController(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@GetMapping("/book/{id}")
	public Map<String, Object> getBookById(@PathVariable String id) {
		return bookDao.getBookById(id);
	}

	@PutMapping("/book/{id}")
	public Book insertBook(@RequestBody Book book) {
		return bookDao.insertBook(book);
	}

	@PostMapping("/book/{id}")
	public Map<String, Object> updateBookById(@RequestBody Book book, @PathVariable String id) {
		return bookDao.updateBookById(book, id);
	}

	@DeleteMapping("/book/{id}")
	public void deleteBookById(@PathVariable String id) {
		bookDao.deleteBookById(id);
	}

}
