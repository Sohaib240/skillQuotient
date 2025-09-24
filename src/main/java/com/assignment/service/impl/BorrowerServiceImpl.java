package com.assignment.service.impl;

import com.assignment.dto.BorrowerDto;
import com.assignment.entity.Borrower;
import com.assignment.exception.ConflictException;
import com.assignment.exception.ResourceNotFoundException;
import com.assignment.repository.BorrowerRepository;
import com.assignment.service.BorrowerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final ModelMapper modelMapper;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
        this.modelMapper = new ModelMapper();
    }

    @Transactional
    @Override
    public BorrowerDto create(BorrowerDto dto) {
        borrowerRepository.findByEmail(dto.getEmail()).ifPresent(b -> {
            throw new ConflictException("Email already exists");
        });

        Borrower borrower = modelMapper.map(dto, Borrower.class);
        borrower = borrowerRepository.save(borrower);
        return modelMapper.map(borrower, BorrowerDto.class);
    }

    @Override
    public Borrower getById(Long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));
        return borrower;
    }

    @Override
    public List<BorrowerDto> getAllBorrowers() {
        return modelMapper.map(borrowerRepository.findAll(), new TypeToken<List<BorrowerDto>>() {}.getType());
    }
}