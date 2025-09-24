package com.assignment.service.validator;

import com.assignment.dto.BookDto;
import com.assignment.entity.Book;
import com.assignment.repository.BookRepository;
import com.assignment.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class BookValidator implements Validator {

    private final BookRepository bookRepository;

    public BookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BookDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookDto bookDTO = (BookDto) target;
        Optional<Book> existingBook = bookRepository.findFirstByIsbn(bookDTO.getIsbn());
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            if (!book.getTitle().equals(bookDTO.getTitle()) || !book.getAuthor().equals(bookDTO.getAuthor())) {
                errors.rejectValue("isbn", "invalid.isbn",
                        "A book with this ISBN already exists but title/author does not match");
            }
        }
    }
}
