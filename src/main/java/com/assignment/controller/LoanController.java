package com.assignment.controller;

import com.assignment.dto.LoanDto;
import com.assignment.entity.Loan;
import com.assignment.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/borrowers/{borrowerId}")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/borrow/{bookId}")
    public ResponseEntity<LoanDto> borrow(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        return ResponseEntity.ok(loanService.borrowBook(borrowerId, bookId));
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<LoanDto> returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        return ResponseEntity.ok(loanService.returnBook(borrowerId, bookId));
    }
}
