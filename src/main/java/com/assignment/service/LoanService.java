package com.assignment.service;

import com.assignment.dto.LoanDto;
import com.assignment.entity.Loan;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {
    LoanDto borrowBook(Long borrowerId, Long bookId);
    LoanDto returnBook(Long borrowerId, Long bookId);
}
