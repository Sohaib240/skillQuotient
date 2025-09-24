package com.assignment.service.impl;

import com.assignment.dto.BookDto;
import com.assignment.entity.Book;
import com.assignment.repository.BookRepository;
import com.assignment.repository.LoanRepository;
import com.assignment.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.assignment.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    @Override
    public BookDto create(BookDto dto) {
        Book book = modelMapper.map(dto, Book.class);
        book = bookRepository.save(book);

        BookDto outDto = modelMapper.map(book, BookDto.class);
        outDto.setBorrowed(false);
        return outDto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> {
            BookDto dto = modelMapper.map(book, BookDto.class);
            boolean borrowed = loanRepository.findActiveLoanByBookId(book.getId()).isPresent();
            dto.setBorrowed(borrowed);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }
}
