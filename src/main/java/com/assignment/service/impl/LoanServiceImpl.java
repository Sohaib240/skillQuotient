package com.assignment.service.impl;

import com.assignment.dto.LoanDto;
import com.assignment.entity.Book;
import com.assignment.entity.Loan;
import com.assignment.exception.ConflictException;
import com.assignment.exception.ResourceNotFoundException;
import com.assignment.repository.LoanRepository;
import com.assignment.service.BookService;
import com.assignment.service.BorrowerService;
import com.assignment.service.LoanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final BorrowerService borrowerService;
    private final BookService bookService;

    public LoanServiceImpl(LoanRepository loanRepository,
                       BorrowerService borrowerService,
                       BookService bookService) {
        this.loanRepository = loanRepository;
        this.borrowerService = borrowerService;
        this.bookService = bookService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public LoanDto borrowBook(Long borrowerId, Long bookId) {
        Book book = bookService.getById(bookId);

        loanRepository.findActiveLoanByBookId(bookId).ifPresent(l -> {
            throw new ConflictException("Book is already borrowed");
        });

        Loan loan = new Loan();
        loan.setBorrower(borrowerService.getById(borrowerId));
        loan.setBook(book);
        loan.setBorrowedAt(Instant.now());

        loan = loanRepository.save(loan);

        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setBorrowerId(loan.getBorrower().getId());
        dto.setBorrowerName(loan.getBorrower().getName());
        dto.setBookId(book.getId());
        dto.setBookTitle(book.getTitle());
        dto.setBorrowedAt(loan.getBorrowedAt());
        dto.setReturnedAt(loan.getReturnedAt());

        return dto;
    }

    @Transactional
    public LoanDto returnBook(Long borrowerId, Long bookId) {
        Loan loan = loanRepository.findActiveLoanByBookIdAndBorrowerId(bookId, borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Active loan not found for this borrower and book"));

        loan.setReturnedAt(Instant.now());
        loan = loanRepository.save(loan);

        LoanDto dto = new LoanDto();
        dto.setId(loan.getId());
        dto.setBorrowerId(loan.getBorrower().getId());
        dto.setBorrowerName(loan.getBorrower().getName());
        dto.setBookId(loan.getBook().getId());
        dto.setBookTitle(loan.getBook().getTitle());
        dto.setBorrowedAt(loan.getBorrowedAt());
        dto.setReturnedAt(loan.getReturnedAt());

        return dto;
    }
}
