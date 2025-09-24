package com.assignment.service;

import com.assignment.dto.BorrowerDto;
import com.assignment.entity.Borrower;
import org.springframework.stereotype.Service;

@Service
public interface BorrowerService {
    public BorrowerDto create(BorrowerDto dto);

    public Borrower getById(Long id);
}
