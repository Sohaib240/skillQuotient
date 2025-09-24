package com.assignment.controller;

import com.assignment.dto.BookDto;
import com.assignment.service.BookService;
import com.assignment.service.validator.BookValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @PostMapping
    public ResponseEntity<?> createNewBook(@Valid @RequestBody BookDto dto, BindingResult bindingResult) {
        bookValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.status(201).body(bookService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.listAll());
    }
}
