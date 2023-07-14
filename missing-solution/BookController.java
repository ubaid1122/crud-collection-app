package com.wecreateproblems.crudcollectionapp.controller;

import com.wecreateproblems.crudcollectionapp.entity.Book;
import com.wecreateproblems.crudcollectionapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void createBook(@RequestBody Book book) {
        // add book to collection
    }

    @GetMapping
    public List<Book> getAllBooks() {
        // return all books from collection
    }
}
