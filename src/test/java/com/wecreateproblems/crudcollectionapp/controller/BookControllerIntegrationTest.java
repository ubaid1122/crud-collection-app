package com.wecreateproblems.crudcollectionapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecreateproblems.crudcollectionapp.entity.Book;
import com.wecreateproblems.crudcollectionapp.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setName("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPrice(10.99);

        //provided
        bookService.createBook(book);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("The Great Gatsby"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("F. Scott Fitzgerald"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("10.99"));
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setName("The Great Gatsby");
        book.setAuthor("F. Scott Fitzgerald");
        book.setPrice(10.99);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));
        resultActions.andExpect(status().isOk());

        List<Book> bookList = bookService.getAllBooks();
        assertEquals(1, bookList.size());
        assertEquals(book, bookList.get(0));
    }

}
