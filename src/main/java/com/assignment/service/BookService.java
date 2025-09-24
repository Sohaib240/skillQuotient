package com.assignment.service;

import com.assignment.dto.BookDto;
import com.assignment.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public List<BookDto> getAllBooks();
    public Book getById(Long id);
    public BookDto create(BookDto dto);
}
