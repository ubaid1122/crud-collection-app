package com.wecreateproblems.crudcollectionapp.service;

import com.wecreateproblems.crudcollectionapp.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final Map<Long, Book> books = new HashMap<>();

    public void createBook(Book book) {
        // add a book to map
    }

    public List<Book> getAllBooks() {
        // return list of books from map
    }
}
