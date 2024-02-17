package org.example.seminar5task2.controller;

import lombok.AllArgsConstructor;
import org.example.seminar5task2.model.Book;
import org.example.seminar5task2.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBook() {
        return bookService.getAllBooks();
    }
}
