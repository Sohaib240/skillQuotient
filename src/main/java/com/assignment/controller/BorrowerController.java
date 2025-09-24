package com.assignment.controller;

import com.assignment.dto.BorrowerDto;
import com.assignment.entity.Borrower;
import com.assignment.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService; }

    @PostMapping
    public ResponseEntity<BorrowerDto> createBorrower(@Valid @RequestBody BorrowerDto dto) {
        return ResponseEntity.status(201).body(borrowerService.create(dto));
    }
}
