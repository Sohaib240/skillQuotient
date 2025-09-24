package com.assignment.service.validator;

import com.assignment.dto.BookDto;
import com.assignment.entity.Book;
import com.assignment.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookValidatorTest {

    @InjectMocks
    private BookValidator bookValidator;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validate_NewBook_NoErrors() {
        BookDto bookDTO = new BookDto(null, "1234567890", "Spring Boot 101", "Jane Smith", false);
        when(bookRepository.findFirstByIsbn("1234567890")).thenReturn(Optional.empty());

        Errors errors = new BeanPropertyBindingResult(bookDTO, "bookDto");
        bookValidator.validate(bookDTO, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    void validate_DuplicateIsbn_TitleMismatch_Error() {
        BookDto bookDTO = new BookDto(null, "1234567890", "Different Title", "Jane Smith", false);
        Book existingBook = new Book(1L, "1234567890", "Spring Boot 101", "Jane Smith");
        when(bookRepository.findFirstByIsbn("1234567890")).thenReturn(Optional.of(existingBook));

        Errors errors = new BeanPropertyBindingResult(bookDTO, "bookDTO");
        bookValidator.validate(bookDTO, errors);

        assertTrue(errors.hasErrors());
        assertEquals("invalid.isbn", errors.getFieldError("isbn").getCode());
    }

    @Test
    void validate_DuplicateIsbn_TitleAndAuthorMatch_NoError() {
        BookDto bookDTO = new BookDto(null, "1234567890", "Spring Boot 101", "Jane Smith", false);
        Book existingBook = new Book(1L, "1234567890", "Spring Boot 101", "Jane Smith");
        when(bookRepository.findFirstByIsbn("1234567890")).thenReturn(Optional.of(existingBook));

        Errors errors = new BeanPropertyBindingResult(bookDTO, "bookDTO");
        bookValidator.validate(bookDTO, errors);

        assertFalse(errors.hasErrors());
    }
}