package com.assignment.service;

import com.assignment.dto.BorrowerDto;
import com.assignment.entity.Borrower;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BorrowerService {
    BorrowerDto create(BorrowerDto dto);
    List<BorrowerDto> getAllBorrowers();
    Borrower getById(Long id);
}
